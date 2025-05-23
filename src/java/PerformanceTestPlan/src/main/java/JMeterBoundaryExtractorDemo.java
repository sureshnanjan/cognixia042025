import org.apache.jmeter.extractor.BoundaryExtractor;
import org.apache.jmeter.protocol.http.sampler.HTTPSamplerProxy;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.samplers.Sampler;
import org.apache.jmeter.threads.JMeterContext;
import org.apache.jmeter.threads.JMeterContextService;
import org.apache.jmeter.threads.JMeterVariables;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.collections.HashTree;
import org.apache.jmeter.testelement.TestPlan;
import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.control.LoopController;
import org.apache.jmeter.control.gui.LoopControlPanel;
import org.apache.jmeter.control.gui.TestPlanGui;
import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.protocol.http.control.gui.HttpTestSampleGui;
import org.apache.jmeter.reporters.ResultCollector;
import org.apache.jmeter.reporters.Summariser;
import org.apache.jmeter.save.SaveService;
import org.apache.jmeter.testelement.TestElement;
import org.apache.jmeter.threads.ThreadGroup;
import org.apache.jmeter.threads.gui.ThreadGroupGui;
import org.apache.jmeter.visualizers.ViewResultsFullVisualizer;
import org.apache.jorphan.collections.HashTree;

import java.io.File;
import java.io.FileOutputStream;

/**
 * This class demonstrates how to use JMeter's Boundary Extractor programmatically
 * in two different ways:
 * 1. Direct API usage - for programmatic extraction from response data
 * 2. Test plan integration - for adding extractors to JMeter test plans programmatically
 */
public class JMeterBoundaryExtractorDemo {
    
    /**
     * Example of direct programmatic use of BoundaryExtractor
     * This is useful when you want to extract data without running a full JMeter test
     */
    public static void directExtractorUsage() {
        // Sample response data to extract from
        String responseData = "<html><body><div id='content'>This is the content we want to extract</div></body></html>";
        
        // Create a boundary extractor
        BoundaryExtractor extractor = new BoundaryExtractor();
        extractor.setName("ContentExtractor");
        extractor.setRefName("EXTRACTED_CONTENT"); // Variable name to store the result
        extractor.setLeftBoundary("<div id='content'>");
        extractor.setRightBoundary("</div>");
        extractor.setMatchNumber(1); // Get the first match
        extractor.setDefaultValue("Not found"); // Default if nothing matches
        
        // Create and setup JMeter context and variables (required for extractor to work)
        JMeterContext context = JMeterContextService.getContext();
        JMeterVariables vars = new JMeterVariables();
        context.setVariables(vars);
        
        // Create a sample result to hold the response data
        SampleResult result = new SampleResult();
        result.setResponseData(responseData, "UTF-8");
        context.setPreviousResult(result);
        
        // Create a dummy sampler (needed by the extractor)
        Sampler sampler = new HTTPSamplerProxy();
        context.setCurrentSampler(sampler);
        
        // Process the extraction
        extractor.process();
        
        // Get the extracted value
        String extractedValue = vars.get("EXTRACTED_CONTENT");
        System.out.println("Extracted value: " + extractedValue);
        
        // Get match numbers if you specified multiple matches
        String matchCount = vars.get("EXTRACTED_CONTENT_matchNr");
        System.out.println("Match count: " + matchCount);
    }
    
    /**
     * Example of programmatically adding a boundary extractor to a JMeter test plan
     * This is useful when you want to create or modify JMeter test plans with code
     */
    public static void addExtractorToTestPlan() throws Exception {
        // Initialize JMeter
        File jmeterHome = new File(System.getProperty("jmeter.home", "/path/to/jmeter"));
        String slash = System.getProperty("file.separator");
        
        // Set JMeter properties
        JMeterUtils.setJMeterHome(jmeterHome.getPath());
        JMeterUtils.loadJMeterProperties(jmeterHome + slash + "bin" + slash + "jmeter.properties");
        JMeterUtils.initLogging();
        JMeterUtils.initLocale();
        
        // Create a test plan
        TestPlan testPlan = new TestPlan("Create JMeter Test Plan");
        testPlan.setProperty(TestElement.TEST_CLASS, TestPlan.class.getName());
        testPlan.setProperty(TestElement.GUI_CLASS, TestPlanGui.class.getName());
        //testPlan.setUserDefinedVariables();
        
        // Create a thread group
        ThreadGroup threadGroup = new ThreadGroup();
        threadGroup.setName("Example Thread Group");
        threadGroup.setNumThreads(1);
        threadGroup.setRampUp(1);
        threadGroup.setSamplerController(new LoopController());
        threadGroup.setProperty(TestElement.TEST_CLASS, ThreadGroup.class.getName());
        threadGroup.setProperty(TestElement.GUI_CLASS, ThreadGroupGui.class.getName());
        
        // Loop controller
        LoopController loopController = new LoopController();
        loopController.setLoops(1);
        loopController.setFirst(true);
        loopController.setProperty(TestElement.TEST_CLASS, LoopController.class.getName());
        loopController.setProperty(TestElement.GUI_CLASS, LoopControlPanel.class.getName());
        loopController.initialize();
        threadGroup.setSamplerController(loopController);
        
        // Create an HTTP Sampler
        HTTPSamplerProxy httpSampler = new HTTPSamplerProxy();
        httpSampler.setDomain("example.com");
        httpSampler.setPort(80);
        httpSampler.setPath("/");
        httpSampler.setMethod("GET");
        httpSampler.setName("Home Page");
        httpSampler.setProperty(TestElement.TEST_CLASS, HTTPSamplerProxy.class.getName());
        httpSampler.setProperty(TestElement.GUI_CLASS, HttpTestSampleGui.class.getName());
        
        // Create a Boundary Extractor
        BoundaryExtractor boundaryExtractor = new BoundaryExtractor();
        boundaryExtractor.setName("Extract Page Title");
        boundaryExtractor.setRefName("PAGE_TITLE");
        boundaryExtractor.setLeftBoundary("<title>");
        boundaryExtractor.setRightBoundary("</title>");
        boundaryExtractor.setMatchNumber(1);
        boundaryExtractor.setDefaultValue("No Title Found");
        boundaryExtractor.setUseField("response_data"); // Extract from response body
        
        // Create test tree
        HashTree testPlanTree = new HashTree();
        testPlanTree.add(testPlan);
        HashTree threadGroupHashTree = testPlanTree.add(testPlan, threadGroup);
        HashTree samplerHashTree = threadGroupHashTree.add(httpSampler);
        samplerHashTree.add(boundaryExtractor); // Add extractor as a child of the sampler
        
        // Add a result collector for viewing results
        Summariser summer = null;
        String summariserName = JMeterUtils.getPropDefault("summariser.name", "summary");
        if (summariserName.length() > 0) {
            summer = new Summariser(summariserName);
        }
        
        ResultCollector resultCollector = new ResultCollector(summer);
        resultCollector.setProperty(TestElement.TEST_CLASS, ResultCollector.class.getName());
        resultCollector.setProperty(TestElement.GUI_CLASS, ViewResultsFullVisualizer.class.getName());
        resultCollector.setFilename("test-results.jtl");
        testPlanTree.add(testPlanTree.getArray()[0], resultCollector);
        
        // Save the test plan to a JMX file
        SaveService.saveTree(testPlanTree, new FileOutputStream("example_test_plan.jmx"));
        
        // Run the test plan
        StandardJMeterEngine jmeter = new StandardJMeterEngine();
        jmeter.configure(testPlanTree);
        jmeter.run();
        
        System.out.println("Test completed!");
    }
    
    /**
     * Example of using BoundaryExtractor programmatically as a post-processor
     * to extract values from API responses
     */
    public static class ApiResponseProcessor {
        private final BoundaryExtractor extractor;
        private final JMeterContext context;
        private final JMeterVariables vars;
        
        public ApiResponseProcessor(String leftBoundary, String rightBoundary, String variableName) {
            // Initialize extractor
            this.extractor = new BoundaryExtractor();
            extractor.setName("API_Extractor");
            extractor.setRefName(variableName);
            extractor.setLeftBoundary(leftBoundary);
            extractor.setRightBoundary(rightBoundary);
            extractor.setMatchNumber(1);
            extractor.setDefaultValue("");
            
            // Setup JMeter context
            this.context = JMeterContextService.getContext();
            this.vars = new JMeterVariables();
            context.setVariables(vars);
            context.setCurrentSampler(new HTTPSamplerProxy());
        }
        
        /**
         * Process an API response and extract the value
         * @param responseData The API response data
         * @return The extracted value
         */
        public String processResponse(String responseData) {
            // Create a sample result with the response data
            SampleResult result = new SampleResult();
            result.setResponseData(responseData, "UTF-8");
            context.setPreviousResult(result);
            
            // Run the extractor
            extractor.process();
            
            // Return the extracted value
            return vars.get(extractor.getRefName());
        }
        
        /**
         * Process an API response and extract multiple values
         * @param responseData The API response data
         * @return Array of extracted values
         */
        public String[] processResponseMultiple(String responseData) {
            // Set to extract all matches
            extractor.setMatchNumber(0);
            
            // Create a sample result with the response data
            SampleResult result = new SampleResult();
            result.setResponseData(responseData, "UTF-8");
            context.setPreviousResult(result);
            
            // Run the extractor
            extractor.process();
            
            // Get the match count
            String matchCountStr = vars.get(extractor.getRefName() + "_matchNr");
            if (matchCountStr == null || matchCountStr.isEmpty()) {
                return new String[0];
            }
            
            int matchCount = Integer.parseInt(matchCountStr);
            String[] results = new String[matchCount];
            
            // Get all matches
            for (int i = 1; i <= matchCount; i++) {
                results[i-1] = vars.get(extractor.getRefName() + "_" + i);
            }
            
            return results;
        }
    }
    
    public static void main(String[] args) {
        try {
            System.out.println("=== Direct Extractor Usage Example ===");
            directExtractorUsage();
            
            System.out.println("\n=== API Response Processor Example ===");
            ApiResponseProcessor processor = new ApiResponseProcessor(
                "\"name\":\"", "\"", "USER_NAME");
                
            String jsonResponse = "{\"id\":123,\"name\":\"John Doe\",\"email\":\"john@example.com\"}";
            String extractedName = processor.processResponse(jsonResponse);
            System.out.println("Extracted name: " + extractedName);
            
            System.out.println("\n=== Multiple Value Extraction Example ===");
            ApiResponseProcessor multiProcessor = new ApiResponseProcessor(
                "<tag>", "</tag>", "ALL_TAGS");
                
            String xmlResponse = "<data><tag>First</tag><other>ignore</other><tag>Second</tag><tag>Third</tag></data>";
            String[] tags = multiProcessor.processResponseMultiple(xmlResponse);
            System.out.println("Extracted " + tags.length + " tags:");
            for (String tag : tags) {
                System.out.println(" - " + tag);
            }
            
            System.out.println("\n=== Adding Extractor to Test Plan Example ===");
            // Uncomment to run this example (requires JMeter in classpath)
            // addExtractorToTestPlan();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

import org.apache.jmeter.control.LoopController;
import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.extractor.BoundaryExtractor;
import org.apache.jmeter.extractor.gui.BoundaryExtractorGui;
import org.apache.jmeter.protocol.http.control.Header;
import org.apache.jmeter.protocol.http.control.HeaderManager;
import org.apache.jmeter.protocol.http.sampler.HTTPSamplerProxy;
import org.apache.jmeter.protocol.http.util.HTTPArgument;
import org.apache.jmeter.reporters.ResultCollector;
import org.apache.jmeter.reporters.Summariser;
import org.apache.jmeter.save.SaveService;
import org.apache.jmeter.testelement.TestElement;
import org.apache.jmeter.testelement.TestPlan;
import org.apache.jmeter.threads.ThreadGroup;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.collections.HashTree;
import org.apache.jmeter.config.Arguments;

import java.io.File;
import java.io.FileOutputStream;

public class ProgrammaticJMeterTest {
    public static void main(String[] args) throws Exception {
        // Set JMeter Home directory (update this path)
        String jmeterHome = "C:\\Trainings-2021\\Vendors\\Cognixia\\apache-jmeter-5.6.3\\apache-jmeter-5.6.3";
        
        // Initialize JMeter
        File jmeterProperties = new File(jmeterHome + "/bin/jmeter.properties");
        if (!jmeterProperties.exists()) {
            throw new RuntimeException("JMeter properties file not found: " + jmeterProperties.getAbsolutePath());
        }
        JMeterUtils.setJMeterHome(jmeterHome);
        JMeterUtils.loadJMeterProperties(jmeterProperties.getPath());
        JMeterUtils.initLocale();
        System.setProperty("java.awt.headless", "true");
        JMeterUtils.setProperty("jmeter.headlessMode", "true");
        
        // Create a Test Plan
        TestPlan testPlan = new TestPlan("ESHOP with Java Code");
        testPlan.setProperty(TestElement.TEST_CLASS, TestPlan.class.getName());
        testPlan.setProperty(TestElement.GUI_CLASS, TestPlan.class.getName());
        Arguments udvArguments = new Arguments();
        udvArguments.addArgument("app_user","demouser@microsoft.com");
        udvArguments.addArgument("app_pwd","Pass@word1");
        testPlan.setUserDefinedVariables(udvArguments);
        
        // Create a Thread Group
        ThreadGroup threadGroup = new ThreadGroup();
        threadGroup.setName("Sample Thread Group");
        threadGroup.setNumThreads(1);
        threadGroup.setRampUp(1);
        threadGroup.setSamplerController(new LoopController());
        threadGroup.setProperty(TestElement.TEST_CLASS, ThreadGroup.class.getName());
        threadGroup.setProperty(TestElement.GUI_CLASS, ThreadGroup.class.getName());
        
        // Loop Controller
        LoopController loopController = new LoopController();
        loopController.setLoops(1);
        loopController.setFirst(true);
        loopController.setProperty(TestElement.TEST_CLASS, LoopController.class.getName());
        loopController.setProperty(TestElement.GUI_CLASS, LoopController.class.getName());
        loopController.initialize();
        threadGroup.setSamplerController(loopController);
        
        // Create HTTP Sampler - POST with form data
        HTTPSamplerProxy loginVisitRequest = new HTTPSamplerProxy();
        loginVisitRequest.setDomain("35.193.6.1");
        loginVisitRequest.setPort(5106);
        loginVisitRequest.setPath("/Identity/Account/Login");
        loginVisitRequest.setMethod("GET");
        loginVisitRequest.setName("HTTP Request with @ Symbol");
        loginVisitRequest.setProtocol("http");
        loginVisitRequest.setFollowRedirects(true);
        loginVisitRequest.setUseKeepAlive(true);
        loginVisitRequest.setProperty(TestElement.TEST_CLASS, HTTPSamplerProxy.class.getName());
        loginVisitRequest.setProperty(TestElement.GUI_CLASS, HTTPSamplerProxy.class.getName());

        // Do Login Request
        HTTPSamplerProxy doLoginRequest = new HTTPSamplerProxy();
        doLoginRequest.setDomain("35.193.6.1");
        doLoginRequest.setPort(5106);
        doLoginRequest.setPath("/Identity/Account/Login");
        doLoginRequest.setMethod("POST");
        doLoginRequest.setName("Do Login with Credentials");
        doLoginRequest.setProtocol("http");
        doLoginRequest.setFollowRedirects(true);
        doLoginRequest.setUseKeepAlive(true);
        doLoginRequest.setProperty(TestElement.TEST_CLASS, HTTPSamplerProxy.class.getName());
        doLoginRequest.setProperty(TestElement.GUI_CLASS, HTTPSamplerProxy.class.getName());

        // Create a Boundary Extractor
        BoundaryExtractor tokenExtractor = new BoundaryExtractor();
        tokenExtractor.setName("Extract Validation Token");
        tokenExtractor.setRefName("validationToken");
        tokenExtractor.setLeftBoundary("input name=\"__RequestVerificationToken\" type=\"hidden\" value=");
        tokenExtractor.setRightBoundary("/>");
        tokenExtractor.setMatchNumber(1);
        tokenExtractor.setDefaultValue("");
        tokenExtractor.setUseField("response_data"); // Extract from response body
        tokenExtractor.setProperty(BoundaryExtractor.TEST_CLASS, BoundaryExtractor.class.getName());
        tokenExtractor.setProperty(BoundaryExtractor.GUI_CLASS, BoundaryExtractorGui.class.getName());
        
        // Add Arguments (form parameters) with proper encoding
        Arguments loginArguments = new Arguments();
        
        // Parameter with @ symbol
        HTTPArgument emailArg = new HTTPArgument();
        emailArg.setName("Input.Email");
        emailArg.setValue("demouser@microsoft.com");
        emailArg.setAlwaysEncoded(true);  // This ensures proper encoding
        emailArg.setUseEquals(true);
        loginArguments.addArgument(emailArg);
        
        // Other parameters
        HTTPArgument passwordArg = new HTTPArgument();
        passwordArg.setName("Input.Password");
        passwordArg.setValue("Pass@word1");
        passwordArg.setAlwaysEncoded(true);
        passwordArg.setUseEquals(true);
        loginArguments.addArgument(passwordArg);

        HTTPArgument validationTokenArg = new HTTPArgument();
        validationTokenArg.setName("__RequestVerificationToken");
        validationTokenArg.setValue(testPlan.getUserDefinedVariables().get("validationToken"));
        validationTokenArg.setAlwaysEncoded(true);
        validationTokenArg.setUseEquals(true);
        loginArguments.addArgument(validationTokenArg);
        
        doLoginRequest.setArguments(loginArguments);
        
        // Create Header Manager
        HeaderManager headerManager = new HeaderManager();
        headerManager.setName("HTTP Header Manager");
        headerManager.add(new Header("Content-Type", "application/x-www-form-urlencoded"));
        //headerManager.add(new Header("Accept", "application/json"));
        headerManager.setProperty(TestElement.TEST_CLASS, HeaderManager.class.getName());
        headerManager.setProperty(TestElement.GUI_CLASS, HeaderManager.class.getName());
        
        // Build the test plan
        HashTree testPlanTree = new HashTree();
        testPlanTree.add(testPlan);
        HashTree threadGroupTree = testPlanTree.add(testPlan, threadGroup);
        HashTree visitTree = threadGroupTree.add(loginVisitRequest);
        visitTree.add(tokenExtractor);
        HashTree doLoginTree = threadGroupTree.add(doLoginRequest);
        doLoginTree.add(headerManager);
        
        // Save the test plan to a JMX file
        SaveService.saveTree(testPlanTree, new FileOutputStream("programmatic_test_plan.jmx"));
        
        // Add a listener to collect and report results
        Summariser summer = null;
        String summariserName = JMeterUtils.getPropDefault("summariser.name", "summary");
        if (!summariserName.isEmpty()) {
            summer = new Summariser(summariserName);
        }
        
        String logFile = "jmeter-results.jtl";
        ResultCollector logger = new ResultCollector(summer);
        logger.setFilename(logFile);
        testPlanTree.add(testPlanTree.getArray()[0], logger);
        
        // Run Test Plan
        StandardJMeterEngine jmeter = new StandardJMeterEngine();
        jmeter.configure(testPlanTree);
        jmeter.run();

        
        System.out.println("Test completed. Results saved to: " + logFile);
    }
}
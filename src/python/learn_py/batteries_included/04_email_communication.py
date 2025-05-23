import smtplib
import imaplib
import email
from email.mime.text import MIMEText
from email.mime.multipart import MIMEMultipart
from email.mime.base import MIMEBase
from email import encoders
import os


class EmailManager:
    """Handle email operations using standard library."""

    def __init__(self, smtp_server, smtp_port, imap_server, imap_port=993):
        self.smtp_server = smtp_server
        self.smtp_port = smtp_port
        self.imap_server = imap_server
        self.imap_port = imap_port

    def send_email(self, sender_email, sender_password, recipient_email,
                   subject, body, attachments=None):
        """Send email with optional attachments."""

        # Create message
        msg = MIMEMultipart()
        msg['From'] = sender_email
        msg['To'] = recipient_email
        msg['Subject'] = subject

        # Add body
        msg.attach(MIMEText(body, 'plain'))

        # Add attachments if provided
        if attachments:
            for file_path in attachments:
                if os.path.isfile(file_path):
                    with open(file_path, "rb") as attachment:
                        part = MIMEBase('application', 'octet-stream')
                        part.set_payload(attachment.read())

                    encoders.encode_base64(part)
                    part.add_header(
                        'Content-Disposition',
                        f'attachment; filename= {os.path.basename(file_path)}'
                    )
                    msg.attach(part)

        # Send email
        try:
            with smtplib.SMTP(self.smtp_server, self.smtp_port) as server:
                server.starttls()
                server.login(sender_email, sender_password)
                server.send_message(msg)
            print(f"Email sent successfully to {recipient_email}")
            return True
        except Exception as e:
            print(f"Error sending email: {e}")
            return False

    def read_emails(self, email_address, password, folder='INBOX', limit=10):
        """Read emails from inbox."""
        try:
            with imaplib.IMAP4_SSL(self.imap_server, self.imap_port) as mail:
                mail.login(email_address, password)
                mail.select(folder)

                # Search for all emails
                status, messages = mail.search(None, 'ALL')
                email_ids = messages[0].split()

                # Get recent emails
                recent_emails = []
                for email_id in email_ids[-limit:]:
                    status, msg_data = mail.fetch(email_id, '(RFC822)')

                    for response_part in msg_data:
                        if isinstance(response_part, tuple):
                            msg = email.message_from_bytes(response_part[1])

                            email_info = {
                                'subject': msg['subject'],
                                'from': msg['from'],
                                'date': msg['date'],
                                'body': self._get_email_body(msg)
                            }
                            recent_emails.append(email_info)

                return recent_emails

        except Exception as e:
            print(f"Error reading emails: {e}")
            return []

    def _get_email_body(self, msg):
        """Extract email body from message."""
        if msg.is_multipart():
            for part in msg.walk():
                if part.get_content_type() == "text/plain":
                    return part.get_payload(decode=True).decode()
        else:
            return msg.get_payload(decode=True).decode()


# Example usage (Gmail configuration)
email_manager = EmailManager(
    smtp_server='smtp.gmail.com',
    smtp_port=587,
    imap_server='imap.gmail.com'
)

# Send email with attachment
# email_manager.send_email(
#     sender_email='your_email@gmail.com',
#     sender_password='your_app_password',
#     recipient_email='recipient@example.com',
#     subject='Test Email',
#     body='This is a test email sent using Python!',
#     attachments=['document.pdf', 'image.jpg']
# )
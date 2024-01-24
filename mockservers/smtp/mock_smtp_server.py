from aiosmtpd.controller import Controller
from email.parser import BytesParser
from io import BytesIO


class CustomHandler:
    async def handle_DATA(self, server, session, envelope):
        print('Message from:', envelope.mail_from)
        print('Message for:', envelope.rcpt_tos)
        print('Message data:')
        data = BytesIO(envelope.content)
        message = BytesParser().parse(data)
        print(message)
        return '250 Message accepted for delivery'


if __name__ == '__main__':
    handler = CustomHandler()
    controller = Controller(handler, hostname='127.0.0.1', port=1025)
    controller.start()
    print("Running mock SMTP server on port 1025...")

    try:
        input("Press Enter to stop the server and exit...")
    except KeyboardInterrupt:
        pass

    controller.stop()
    print("Mock SMTP server stopped")

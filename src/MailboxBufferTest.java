public class MailboxBufferTest {

    public static void main( String [] args ) {

        Buffer sharedLocation = new SafeMailboxBuffer();

        Producer producer = new Producer( sharedLocation );
        Consumer consumer = new Consumer( sharedLocation );

        producer.start();
        consumer.start();
    }
}


class SafeMailboxBuffer implements Buffer {

    private MessageQueue mailBox = new MessageQueue();
    private int occupiedMessages = 0;

    public synchronized void set( int value ) {
        String name = Thread.currentThread().getName();

        mailBox.send( Integer.valueOf( value ) );
        ++occupiedMessages;

        System.err.println( name + " writes to Mailbox: " + value +
                " \t[Mensagens na fila: " + occupiedMessages + "]" );

        notifyAll();
    }

    public synchronized int get() {
        String name = Thread.currentThread().getName();
        Object message = null;

        while ( ( message = mailBox.receive() ) == null ) {
            try {
                System.err.println( "Mailbox vazio. " + name + " aguarda mensagem..." );
                wait();
            }
            catch ( InterruptedException exception ) {
                exception.printStackTrace();
            }
        }

        --occupiedMessages;
        int readValue = ( ( Integer ) message ).intValue();

        System.err.println( name + " reads from Mailbox: " + readValue +
                " \t[Mensagens na fila: " + occupiedMessages + "]" );

        return readValue;
    }
}
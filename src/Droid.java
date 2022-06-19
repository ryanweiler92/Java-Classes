public class Droid{
    String name;
    int batteryLevel;
    public Droid(String droidName){
        name = droidName;
        batteryLevel = 100;
    }

    public String toString(){
        return "Hello, I'm the droid: " + name;
    }

    public void performTask(String task){
        System.out.println(name + " is performing task: " + task);
        batteryLevel = batteryLevel - 10;
    }

    public void energyReport(){
        System.out.println(name + " is current at " + batteryLevel + " power.");
    }

    public void energyTransfer(Droid droidA, Droid droidB){
        if(droidA.batteryLevel > 90){
            System.out.println(droidA.name + " battery level too high for transfer.");
        } else if (droidB.batteryLevel < 10){
            System.out.println(droidB.name + " battery level too low for transfer.");
        } else {
            droidA.batteryLevel = droidA.batteryLevel + 10;
            droidB.batteryLevel = droidB.batteryLevel - 10;
        }

    }

    public static void main(String[] args){

        Droid codey = new Droid("Codey");
        Droid demon = new Droid("DEMON");

        System.out.println(codey);
        codey.performTask("exploding");
        codey.performTask("detonating");
        codey.energyReport();

        System.out.println(demon);
        demon.energyReport();

        codey.energyTransfer(codey, demon);
        codey.energyReport();
        demon.energyReport();

        demon.performTask("self-destruct");
        demon.performTask("self-destruct");
        demon.performTask("self-destruct");
        demon.performTask("self-destruct");
        demon.performTask("self-destruct");
        demon.performTask("self-destruct");
        demon.performTask("self-destruct");
        demon.energyReport();
        codey.performTask("self-implode");
        codey.performTask("self-implode");
        codey.performTask("self-implode");
        codey.energyTransfer(codey, demon);
        codey.energyTransfer(codey, demon);
        demon.energyReport();
        codey.energyTransfer(codey, demon);
    }
}
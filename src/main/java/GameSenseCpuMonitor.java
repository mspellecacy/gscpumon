import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by mspellecacy on 6/6/2016.
 */
public class GameSenseCpuMonitor {

    public static String GAME_NAME = "CPU_MONITOR";
    public static String GAME_DESCRIPTION = "Rival 700 CPU Monitor";
    public static String GAME_EVENT = "CPULOAD";

    //TODO: Implement GameSense objects/constructors/factories/etc...
    private static final String __GAME_JSON = "{  \"game\": \"CPU_MONITOR\",  \"game_display_name\": \"RIVAL 700 CPU Monitor\",  \"icon_color_id\": 5}";
    private static final String __BIND_EVENT_JSON = "{ \"game\": \"CPU_MONITOR\", \"event\": \"CPULOAD\", \"icon_id\": 3, \"handlers\": [ { \"device-type\": \"screened\", \"zone\": \"one\", \"mode\": \"screen\", \"datas\": [ { \"has-text\": true, \"suffix\": \"\" } ] } ] }";
    private static final String __EVENT_JSON = "{ \"game\": \"CPU_MONITOR\", \"event\": \"CPULOAD\", \"data\": { \"value\": \"25\" } }";

    public static void main(String[] args) throws InterruptedException {
        GameSenseCpuMonitor gsCpuMon = new GameSenseCpuMonitor();
        gsCpuMon.monitorCpu();

    }

    //Something to test with that will always change...
    public String getDateTime() {
        //Format it to make it fit on the Rival 700's OLED.
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM H:mm:ss");
        LocalDateTime time = LocalDateTime.now();
        return time.format(formatter);

    }

    public void monitorCpu() throws InterruptedException {
        System.out.println("Rival 700 CPU Monitor Initializing...");
        GameSenseService gsService = new GameSenseService();

        System.out.println("--- Registering CPU Monitor ---");
        gsService.registerGame(__GAME_JSON);

        System.out.println("--- Binding CPU_LOAD Event ---");
        gsService.bindGameEvent(__BIND_EVENT_JSON);

        System.out.println("--- Pushing CPU_LOAD Events ---");
        //gsService.sendGameEvent(__EVENT_JSON);

        //Run until killed...
        while (true) {
            try {
                Double cpuLoad = CPUMonitor.getProcessCpuLoad();
                String value = "Load " + cpuLoad + "%"; // use getDateTime(); if you'd prefer something more practical. :)
                String eventJson = "{ \"game\": \"CPU_MONITOR\", \"event\": \"CPULOAD\", \"data\": { \"value\": \"" + value + "\" } }";
                System.out.println("Current Load: " + cpuLoad);
                gsService.sendGameEvent(eventJson);
            } catch (Exception ex) {
                System.out.println("Error getting CPU Load.");
                ex.printStackTrace();
            }

            //Once a second should be fine...
            Thread.sleep(1000);
        }

    }

}

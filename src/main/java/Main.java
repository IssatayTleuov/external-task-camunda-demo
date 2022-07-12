import org.camunda.bpm.client.ExternalTaskClient;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        ExternalTaskClient client = ExternalTaskClient.create()
                .baseUrl("http://localhost:8080/engine-rest")
                .asyncResponseTimeout(1000)
                .build();

        client.subscribe("find").handler(((externalTask, externalTaskService) -> {
            String friendMail = "john@email.com";
            Map<String, Object> variables = new HashMap<>();

            variables.put("friendMail", friendMail);
            externalTaskService.complete(externalTask, variables);
            System.out.println("The External Task " + externalTask.getId() + " has been completed!");
        })).open();
    }
}

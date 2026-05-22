package com.func.app.trigger;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.TimerTrigger;

import java.time.LocalDateTime;


public class TimerTriggerFunction {

    @FunctionName("TimerTriggerJava")
    public void run(
            @TimerTrigger(
                    name = "req",
                    schedule = "0 */2 * * * *")
            String timerInfo,
            final ExecutionContext context) {
        context.getLogger().info("Java Timer trigger processed a request.");

        context.getLogger().info("Function executed at: " + LocalDateTime.now());
    }
}

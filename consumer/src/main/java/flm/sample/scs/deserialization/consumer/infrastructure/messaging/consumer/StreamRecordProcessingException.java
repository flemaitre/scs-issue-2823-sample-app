package flm.sample.scs.deserialization.consumer.infrastructure.messaging.consumer;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
public class StreamRecordProcessingException extends RuntimeException {

    String recordKey;
    Object recordValue;
    Throwable exception;

    @Builder
    public StreamRecordProcessingException(String message, String recordKey, Object recordValue, Throwable exception) {
        super(message);

        this.recordKey = recordKey;
        this.recordValue = recordValue;
        this.exception = exception;
    }
}

package org.alaguna.learningddd2.input_data.training.domain;

import lombok.EqualsAndHashCode;
import org.alaguna.learningddd2.shared.domain.AggregateRoot;
import org.alaguna.learningddd2.shared.domain.training.TrainingCreatedDomainEvent;

@EqualsAndHashCode(callSuper=false)
public class Training extends AggregateRoot {

    private TrainingId id;
    private TrainingPeriod period;

    public Training(TrainingId id, TrainingPeriod period){
        this.id = id;
        this.period = period;
    }

    public static Training create(TrainingId id, TrainingPeriod period){
        Training training = new Training(id, period);

        training.record(new TrainingCreatedDomainEvent(id.value()));

        return training;
    }

    public TrainingId getId() {
        return id;
    }

    public TrainingPeriod getPeriod() {
        return period;
    }

}

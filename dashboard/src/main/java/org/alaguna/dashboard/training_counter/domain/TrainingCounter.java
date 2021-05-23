package org.alaguna.dashboard.training_counter.domain;

import org.alaguna.dashboard.shared.AggregateRoot;

import java.util.List;

public class TrainingCounter extends AggregateRoot {

    private TrainingCounterId id;
    private TrainingCounterTotal total;
    private List<TrainingId> addedTrainings;

    public TrainingCounter(TrainingCounterId id, TrainingCounterTotal total, List<TrainingId> addedTrainings) {
        this.id = id;
        this.total = total;
        this.addedTrainings = addedTrainings;
    }
}

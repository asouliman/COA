package fr.istic.coa.strategy;

import fr.istic.coa.proxy.Channel;

import java.util.List;
import java.util.ArrayList;

/**
 * @author thomas
 * @author amona
 */
public class AtomicDiffusion implements DiffusionAlgorithm {
    private List<Channel> channels;

    @Override
    public void configure() {
        channels = new ArrayList<>();
    }

    @Override
    public void execute() {

    }
}

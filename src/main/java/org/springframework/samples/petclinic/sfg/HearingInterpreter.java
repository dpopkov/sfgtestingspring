package org.springframework.samples.petclinic.sfg;

import org.springframework.stereotype.Service;

@Service
public class HearingInterpreter {

    private final WordProducer wordProducer;

    public HearingInterpreter(WordProducer wordProducer) {
        this.wordProducer = wordProducer;
        System.out.println("Injected wordProducer type: " + wordProducer.getClass());
    }

    public String whaIHeard() {
        String word = wordProducer.getWord();
        System.out.println(word);
        return word;
    }
}

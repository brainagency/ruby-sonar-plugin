package com.godaddy.sonar.ruby.core;

import org.sonar.squidbridge.recognizer.Detector;
import org.sonar.squidbridge.recognizer.EndWithDetector;
import org.sonar.squidbridge.recognizer.KeywordsDetector;
import org.sonar.squidbridge.recognizer.LanguageFootprint;

import java.util.HashSet;
import java.util.Set;

public class RubyFootPrint implements LanguageFootprint {
    private static final double END_WITH_DETECTOR = 0.95;
    private static final double KEYWORDS_DETECTOR = 0.3;

    private final Set<Detector> detectors = new HashSet<Detector>();

    public RubyFootPrint() {
        detectors.add(new EndWithDetector(END_WITH_DETECTOR, ')', '"', '\''));
        detectors.add(new KeywordsDetector(KEYWORDS_DETECTOR, Ruby.RUBY_KEYWORDS_ARRAY));
    }

    public Set<Detector> getDetectors() {
        return detectors;
    }
}
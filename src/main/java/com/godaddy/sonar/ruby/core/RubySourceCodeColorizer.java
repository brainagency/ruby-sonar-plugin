package com.godaddy.sonar.ruby.core;

import org.sonar.api.web.CodeColorizerFormat;
import org.sonar.colorizer.KeywordsTokenizer;
import org.sonar.colorizer.RegexpTokenizer;
import org.sonar.colorizer.StringTokenizer;
import org.sonar.colorizer.Tokenizer;

import java.util.*;

import static com.godaddy.sonar.ruby.core.Ruby.RUBY_KEYWORDS_ARRAY;
import static com.godaddy.sonar.ruby.core.Ruby.RUBY_RESERVED_VARIABLES_ARRAY;

public class RubySourceCodeColorizer extends CodeColorizerFormat {

    private static final Set<String> RUBY_KEYWORDS = new HashSet<String>();
    private static final Set<String> RUBY_RESERVED_VARIABLES = new HashSet<String>();

    static {
        Collections.addAll(RUBY_KEYWORDS, RUBY_KEYWORDS_ARRAY);
        Collections.addAll(RUBY_RESERVED_VARIABLES, RUBY_RESERVED_VARIABLES_ARRAY);
    }

    public RubySourceCodeColorizer() {
        super(Ruby.KEY);
    }

    @Override
    public List<Tokenizer> getTokenizers() {
        String tagAfter = "</span>";
        KeywordsTokenizer rubyKeyWordsTokenizer = new KeywordsTokenizer("<span class=\"k\">", tagAfter, RUBY_KEYWORDS);
        KeywordsTokenizer rubyVariablesTokenizer = new KeywordsTokenizer("<span class=\"c\">", tagAfter, RUBY_RESERVED_VARIABLES);
        RegexpTokenizer rubyCommentsTokenizer = new RegexpTokenizer("<span class=\"cd\">", tagAfter, "#.*");
        StringTokenizer stringTokenizer = new StringTokenizer("<span class=\"s\">", tagAfter);
        return Collections.unmodifiableList(Arrays.asList(rubyKeyWordsTokenizer, rubyVariablesTokenizer, rubyCommentsTokenizer, stringTokenizer));
    }
}

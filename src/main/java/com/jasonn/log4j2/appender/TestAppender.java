package com.jasonn.log4j2.appender;

import org.apache.logging.log4j.ThreadContext;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.layout.PatternLayout;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by jason-geng on 10/22/17.
 */
@Plugin(name = "Test", category = "core")
public class TestAppender extends AbstractAppender {


    protected TestAppender(String name, Filter filter, Layout<? extends Serializable> layout, boolean ignoreExceptions) {
        super(name, filter, layout, ignoreExceptions);
    }

    @Override
    public void append(LogEvent logEvent) {
        Map<String, String> map = ThreadContext.getContext();
        System.out.println(logEvent.getMessage().getFormattedMessage());
    }

    @PluginFactory
    public static TestAppender createAppender(@PluginAttribute("name") String name, @PluginElement("Layout") Layout<? extends Serializable> layout, @PluginElement("Filter") Filter filter, @PluginAttribute("otherAttribute") String otherAttribute) {
        if(name == null) {
            LOGGER.warn("No name provided for TestAppender");
            return null;
        } else {
            if(layout == null) {
                layout = PatternLayout.createDefaultLayout();
            }

            return new TestAppender(name, filter, (Layout)layout, true);
        }
    }
}

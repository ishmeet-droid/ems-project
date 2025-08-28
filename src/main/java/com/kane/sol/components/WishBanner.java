package com.kane.sol.components;

import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.Parameter;

public class WishBanner {
    @Parameter(required=true) private String text;
    @Parameter(required=true) private String color; // "pink" or "blue"

    boolean beginRender(MarkupWriter writer) {
        writer.element("div", "style", "padding:10px;border-radius:6px;border:1px solid #ccc;background:" + color + ";color:#000;");
        writer.element("strong");
        writer.write(text);
        writer.end(); // </strong>
        writer.end(); //</div>
        return false;
    }
}

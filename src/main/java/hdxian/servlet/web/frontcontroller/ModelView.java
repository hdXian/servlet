package hdxian.servlet.web.frontcontroller;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter @Setter
public class ModelView {

    private String logicalViewName;
    private Map<String, Object> model = new HashMap<>(); // <dataName, data>

    public ModelView(String logicalViewName) {
        this.logicalViewName = logicalViewName;
    }

}

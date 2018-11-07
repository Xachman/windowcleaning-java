package com.gti.windowcleaning.web.controller.customers;

        import com.gti.windowcleaning.data.Customer;
        import com.gti.windowcleaning.model.Model;
        import com.gti.windowcleaning.web.Answer;
        import com.gti.windowcleaning.web.Controller;
        import com.gti.windowcleaning.web.payload.CustomerPayload;
        import com.gti.windowcleaning.web.valid.EmptyPayload;
        import java.util.HashMap;
        import java.util.Map;

/**
 *
 * @author xach
 */
public class EntitiyController extends Controller<EmptyPayload> {

    private Model model;

    public EntityController(Model model) {
        super(EmptyPayload.class, model);
        this.model = model;
    }

    /**
     *
     * @param value
     * @param urlParams
     * @param query
     * @param shouldReturnHtml
     * @return
     */
    @Override
    protected Answer processImpl(EmptyPayload value, Map<String, String> urlParams, Map<String,String> query, boolean shouldReturnHtml) {
        if(urlParams.get(":id") != null) {
            Object result = model.get(new Integer(urlParams.get(":id")));
            return new Answer(200, dataToJson(result));
        }
        Map<String,String> error = new HashMap<>();
        error.put("error", "Customer not valid");
        return new Answer(400, dataToJson(error));
    }


}

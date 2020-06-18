package smartpick;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/picks")
public class PickController {

    @Autowired
    private PickRepository pickRepository;

    @RequestMapping(value = "/getPickYn",
            method = RequestMethod.GET,
            produces = "application/json;charset=UTF-8")

    public @ResponseBody Pick inquiry(@RequestParam(name = "orderId") Long orderId)
            throws Exception {

        System.out.println("##### /pick/inquiry  called #####");
        return pickRepository.findByOrderId(orderId);
    }
}


package smartpick.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@FeignClient(name="smartpick", url="${api.url.smartpick}")
public interface PickService {

    @RequestMapping(method= RequestMethod.GET, path="/picks")
    public @ResponseBody Pick inquiry(@RequestBody Pick pick);

}
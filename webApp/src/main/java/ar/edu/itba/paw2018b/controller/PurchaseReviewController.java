package ar.edu.itba.paw2018b.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PurchaseReviewController {
    @RequestMapping("/purchaseReview")
    public ModelAndView seatPicker(@RequestParam(value = "movieID", required = true) final int id,
                                   @RequestParam(value = "time", required = true) final int time,
                                   @RequestParam(value = "amount", required = true) final int amount,
                                   @RequestParam(value = "seat", required = true) final int seat) {
        final ModelAndView mav = new ModelAndView("purchaseReview");

        return mav;
    }
}

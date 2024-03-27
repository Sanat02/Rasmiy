package com.example.demo.controller;

import com.example.demo.enums.SklonenieType;
import com.example.demo.service.AffixService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/affix")
@RequiredArgsConstructor
public class AffixController {
    private final AffixService affixService;

    @PostMapping("/getAffix/{text}/{skl}")
    public String uploadImage(@PathVariable String text,@PathVariable String skl) {
        SklonenieType sklonenieType=SklonenieType.ILIK;
        if(skl.equals("i")){
            sklonenieType=SklonenieType.ILIK;
        } else if (skl.equals("b")) {
            sklonenieType=SklonenieType.BARYSH;
        } else if (skl.equals("t")) {
            sklonenieType=SklonenieType.TABYSH;
        } else if (skl.equals("j")) {
            sklonenieType=SklonenieType.JATYSH;
        } else if (skl.equals("c")) {
            sklonenieType=SklonenieType.CHYGYSH;
        }
        boolean containsOnlyKyrgyz = text.matches("[А-Яа-яӨөҮүҢң]*");

        if (!containsOnlyKyrgyz) {
            return "Input only kygyz letters!";
        }
        String ending=affixService.addAffix(text,sklonenieType);
        return ending;
    }

}

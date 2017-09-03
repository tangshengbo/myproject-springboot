package com.tangshengbo.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tangshengbo.model.Account;
import com.tangshengbo.service.AccountService;
import com.tangshengbo.util.ResponseGenerator;
import com.tangshengbo.util.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
* Created by CodeGenerator on 2017/09/03.
*/
@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping("/add")
    public ResponseMessage add(Account account) {
        accountService.save(account);
        return ResponseGenerator.genSuccessResult();
    }

    @RequestMapping("/delete")
    public ResponseMessage delete(@RequestParam Integer id) {
        accountService.deleteById(id);
        return ResponseGenerator.genSuccessResult();
    }

    @RequestMapping("/update")
    public ResponseMessage update(Account account) {
        accountService.update(account);
        return ResponseGenerator.genSuccessResult();
    }

    @RequestMapping("/detail")
    public ResponseMessage detail(@RequestParam Integer id) {
        Account account = accountService.findById(id);
        return ResponseGenerator.genSuccessResult(account);
    }

    @RequestMapping("/list")
    public ResponseMessage list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Account> list = accountService.findAll();
        PageInfo<Account> pageInfo = new PageInfo<>(list);
        return ResponseGenerator.genSuccessResult(pageInfo);
    }

    @RequestMapping("/save-batch/{count}")
    public void saveBatch(@PathVariable("count") int count) {
        accountService.saveBatchAccount(count);
    }
}

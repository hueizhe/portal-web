package com.hueizhe.interceptor;



import com.hueizhe.domain.User;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Properties;

@Intercepts({@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})})
public class PasswordEncoderPlugin implements Interceptor {
    private final static Logger logger = LoggerFactory.getLogger(PasswordEncoderPlugin.class);
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object parameter = invocation.getArgs()[1];
        if(parameter instanceof User) {
            User user = (User) parameter;
            String passwod = new BCryptPasswordEncoder().encode(user.getPassword());
            logger.error("user password===============>  "+ passwod);
            user.setPassword(new BCryptPasswordEncoder().encode(
                    user.getPassword()));
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }
}


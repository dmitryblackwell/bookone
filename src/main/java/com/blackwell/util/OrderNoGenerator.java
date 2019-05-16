package com.blackwell.util;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.Random;

public class OrderNoGenerator implements IdentifierGenerator {
    private Random r = new Random();
    private char getRandomLetter(){
        return (char) (r.nextInt(25)+65);
    }

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        StringBuilder sb = new StringBuilder();

        sb.append(getRandomLetter());
        sb.append(getRandomLetter());

        for(int i=0; i<4; ++i)
            sb.append(r.nextInt(9));
        return sb.toString();
    }
}

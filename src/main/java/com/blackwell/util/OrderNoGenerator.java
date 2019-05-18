package com.blackwell.util;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.Random;

public class OrderNoGenerator implements IdentifierGenerator {

    private static Random r = new Random();
    private static char getRandomLetter(){
        return (char) (r.nextInt(25)+65);
    }

    public static String generate() {
        StringBuilder sb = new StringBuilder();

        sb.append(getRandomLetter());
        sb.append(getRandomLetter());

        for(int i=0; i<4; ++i)
            sb.append(r.nextInt(9));
        return sb.toString();
    }

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        return generate();
    }
}

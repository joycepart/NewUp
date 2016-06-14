/*
 * Copyright (C) 2010 The MobileSecurePay Project
 * All right reserved.
 * author: shiqun.shi@alipay.com
 * 
 *  提示：如何获取安全校验码和合作身份者id
 *  1.用您的签约支付宝账号登录支付宝网站(www.alipay.com)
 *  2.点击“商家服务”(https://b.alipay.com/order/myorder.htm)
 *  3.点击“查询合作者身份(pid)”、“查询安全校验码(key)”
 */

package com.qluxstory.qingshe.alipay;

//
// 请参考 Android平台安全支付服务(msp)应用开发接口(4.2 RSA算法签名)部分，并使用压缩包中的openssl RSA密钥生成工具，生成一套RSA公私钥。
// 这里签名时，只需要使用生成的RSA私钥。
// Note: 为安全起见，使用RSA私钥进行签名的操作过程，应该尽量放到商家服务器端去进行。
public final class Keys {

	//合作身份者id，以2088开头的16位纯数字
	public static final String DEFAULT_PARTNER = "";

	//收款支付宝账号
	public static final String DEFAULT_SELLER = "";

	//商户私钥，自助生成
//	public static final String PRIVATE = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBANgMLDyL5iEXO6t7tkBtpGEhxOpMKwQAGLFATR+0YhVvPxWVtYY7pP5f0na8N3uXJvFXyROGDzLFOCbg1X7hseGxmEHiKmC11hXyfj1EU4irkcrrNMttAMkkABA275pZongftIxVHCjeaL0nCKW/jE1mnDqOdntIg9afC1hktlBXAgMBAAECgYBlswU30xsXRJqAFJIsLfCNEJpjOEufbHQzK/OiEwWA1Yc6n+h9MaJvY6fs4Ji1gueAADTVDewlmBTgqEGXoqJan9elbqhKIsCMqm8sgc/GYyIce6NrN2tky+tldWWHzoKKZx1IcqoLnvYkRwIWRHzq+l+LJEa67r9rrZlcAj+FgQJBAPaekhytl3x7B7MVxXyUEa9sG0O1WdziKE3TEd1+L+PCLzAX9JzwZmEDZ4oSeaxlFs0Z8hoj38+s8sLBPfC8ViECQQDgQ+lxB87Ks5o6/qhmTtK49fD/HHjTQ4U1kgmtnKf7rHaYe6cBUuzJKRh+2keSAaj0eDrHyYb/EYWRVNPwI2d3AkEAx/YWnm4aD/JpcclLURF1rXjIOoQGktREvaVQLPktt0wxmpsx2TYKrMpn30nPofptHGvXI17HD8o5I7Qvlsv+QQJBAL+SY6RxRf2zhGkmxlV2udzUAoT6HmBhijYjxO6wJnL/dON2pUelPa3s1AbwkTixAFGtLpEhnpbesdlevBijNasCQQDdV1hrYmJ1lyHCf1xQDEpr9lCmbfgXFYcEG1w/xUbl3AURbv1xYqf7szSRnU/VZab8wn13j1bSVM78DqUbrFcZ";

	public static final String PRIVATE = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAM4zArAW0HR84f0flp+Zyt6ycyIzxXSvSJ2uUpnRXTiW/miXM9HVtbTy/gXc97hRM+Lk6uGgR8MdKDpFMoaqTd8sK44l3m7veV3rso/ApoTZgxIxZOPE4EOzSsUoVPHwBsbTwrDus/rPuQq3dBLsFnnaXZ0mgXxmP7/mzhQLnarVAgMBAAECgYBv4FFnBRnY+iELW2Y39hOSPLQnLHvH0YrOstRyTJwNpi8mxFMDWLacFPMqbzegs745LwcZoAMPo/Q9mWnOkvxoTdYnA3AbHiyVu6TYS3DoXZEpz3APoCgvYNis5DdRR1PhVXb83dVEfrRGqh1o1bZ9klmqCdTy4hWT36+9UStywQJBAPhdXoytVPNPDG/auOrMA5JGeDYyEZ/PgaKA/dQhMUULoTRbZmm1vZTqxWPUkeVG43iaRCSG7cVg9q0CgtzVu/0CQQDUicyFkoG+BXUeoljp+YWYNKwP5Az2koopRCD/4UsJqRxtKbuh2QefVYZ3B5odsh4ok2G8tl9oF3X52hpXVmW5AkEAkgkcmi0lATeewXpjNrQk+XJ0JrHECSrTN8EO/xdRSB7xd76ydj/FrHVrASsxahYHlJdor+2ii2dbRBlw5vbJ5QJAK3QM65Y7jnUhL+UzVorcZHUIZKtUdykYtD0onggaxlvb4vmwUfPEWjArMLTOLpoXDmarieCjeu2pAi80SXzxiQJBALm2+9pJVvIQXNbzpgd4cGy6BAdm7barRs1Lwl9ZXtPfkXVJTqunPLDMmmtQWOoCbK2K1AYLUuqKeKdmrFvHpQw=";
//	public static final String PRIVATE ="MIICXQIBAAKBgQDOMwKwFtB0fOH9H5afmcresnMiM8V0r0idrlKZ0V04lv5olzPR1bW08v4F3Pe4UTPi5OrhoEfDHSg6RTKGqk3fLCuOJd5u73ld67KPwKaE2YMSMWTjxOBDs0rFKFTx8AbG08Kw7rP6z7kKt3QS7BZ52l2dJoF8Zj+/5s4UC52q1QIDAQABAoGAb+BRZwUZ2PohC1tmN/YTkjy0Jyx7x9GKzrLUckycDaYvJsRTA1i2nBTzKm83oLO+OS8HGaADD6P0PZlpzpL8aE3WJwNwGx4slbuk2Etw6F2RKc9wD6AoL2DYrOQ3UUdT4VV2/N3VRH60RqodaNW2fZJZqgnU8uIVk9+vvVErcsECQQD4XV6MrVTzTwxv2rjqzAOSRng2MhGfz4GigP3UITFFC6E0W2Zptb2U6sVj1JHlRuN4mkQkhu3FYPatAoLc1bv9AkEA1InMhZKBvgV1HqJY6fmFmDSsD+QM9pKKKUQg/+FLCakcbSm7odkHn1WGdweaHbIeKJNhvLZfaBd1+doaV1ZluQJBAJIJHJotJQE3nsF6Yza0JPlydCaxxAkq0zfBDv8XUUge8Xe+snY/xax1awErMWoWB5SXaK/tootnW0QZcOb2yeUCQCt0DOuWO451IS/lM1aK3GR1CGSrVHcpGLQ9KJ4IGsZb2+L5sFHzxFowKzC0zi6aFw5mq4ngo3rtqQIvNEl88YkCQQC5tvvaSVbyEFzW86YHeHBsugQHZu22q0bNS8JfWV7T35F1SU6rpzywzJprUFjqAmytitQGC1LqininZqxbx6UM";
	public static final String RSA_PRIVATE = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBANUXdciLy5btnSOwWeHhEhbeP0VclutcAhWkUMf4YvfDXcf6DcEbZN4EZdinZ99XXkxc1yib3VsQzVaejdQdWzSIGEZuPDt3/wWH1RdE369RxkxFn1xj4fEmsSz57AFWdrKwDDZp0a1cWuFnyH9Dhd2magAMUfgftZnzy1rDeOY1AgMBAAECgYBrbo16U0iMSjWsNY/LOp7VGq2Jebm45UVDxfjYKJk00rFoTeA7WGVNLahvR08uIa0wEKqFFbfO3rS3k+5rX3WIJYJMlrL3dypoM2LAJpbCqADRljbOLCvYM7/wFv1aCgTfz0tPoATdK3FXZtmsrEJgPDZbDlm7c5x2hel319FwwQJBAPmUX9+uIZJFHTYQvqN+s0gWcYNU2DHBp5qab9poz5KQf1I1IebL/J9PaYjdHfO1+EoeRfNTZvuIfLO0JXkXQjECQQDaksqaLjIdzo2NxHlJDYuCKfMsJ/EpxBV0AdIPAYRJKCkHjODuX8BHSZmqdR5+dFGwE9q5/Z2Q/TUoWduhCj9FAkEA5vYP4wdZueqLpcTJJrPRZRyf31Fc4G69k6znZc8oiBGAQas5vEN5fFQY7bbySN5U7wIk6ZB89LjR7dr/8pc7gQJBANIDHTxLisftAUHoOAkjRJqKqCYTMrtItrxCS40zjeGk/tdMW4tu4Rcd9tI7ulMSfLLMQOtYbh8zy/G7dTYk3GkCQHYJO+OcHjxBECYsfPO8pT/jhI1Yc2NjkfBAxysnlZp+VH7xMU+YP6+iy+M3c0miYUqELmaxe0+UsuckUb9vJIA=";
	public static final String PUBLIC = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";

}

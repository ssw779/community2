package com.zb.config;

/**
 * 支付宝
 */
public class AlipayConfig {
    // 商户appid
    public static String APPID = "2016102400750982";
    // 私钥 pkcs8格式的
    public static String RSA_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCGCI4sfERbUS+Fe48qM+aRrCj7JaTqfSFzi+VC48ZcRnrZnh8BsWrFsaOawahE78OHOg6ByeOLkcRBoFlX5c+ud57HlXI/1Ka54MKfaB/LVKbPpjvGS3rgZgUK5B+rlKEwz3IPE6eaIiss5YNyn5DeN8vj0XhyAj8j1sNGzQe5x+e2PXZpwFTSaVDNzntjLKHL/ZbFGUwrsKCHoacMG7ZlSOuzD/kHezFITl9gMorrmn/1kZp5J0wItNUtVnRlHTuwthfk8qIpuotC7Kj8Fuljxk5Q7hGwIML8pF9GUg1Dp0ZNroHKLLnhvqA57HbIcN80ilT9QE30FY5GBA+V/8IlAgMBAAECggEAQ5pHE1ruI1zUajAC6pdY4PqDhQDcqETKvXu++7jk0J/f0Kk3HGR+5Q2O5jxtAbAo36jBrmbEtR2lbjBRP8syr923oRTYlidMnI4nu70J5EVjCXLfzUk+xbXPL0oWSzVhA7xt2/re2xgGzNNKNGMU73AXD2YUqPRHIm1s+uXo0qOtYPItwm85H44pn5vKoJkMfCDRik20OYb7k6/ZU5OywmjS0Ni3HF1Tw59VP7U4qbCbTeq2xF5AfB3c/pv3I8zDpQqILvTKjMLwE2Z1j8fMcuoR43bEN+Z05d2IwPHeKVQWUD2lDMhhmA2UbSt3NsqQluwKX327vlorUMHO3pTJVQKBgQC/Huoa3AA77pRUG41P5UjbUchFfr+HNOwiEQBl6KBVITscOB7XbhBSTHq8LcEWMUyrqwhE+BKJSsDdP9lP81kzXQZPXWYY7QaG9GaNzUEqEYZNCbK9f8TumHZD+WRzUzdUKq8NPfNyXbJFc81ohO1jGHrPcEmceE8p/0jxag2KZwKBgQCziItihOf2Y6Hc99pJs2B+qt2clzvs0u5uObMFRtoGYZ5a7uwFJQ7k9InJC3/VvvLgPLRPxcS2hJ/QZS7YgIMo5Q1998EAlf7U4i3247tr4+RpC3/y9wULYjubyeAzxSCO2vMVtAcrHtjrJOq41opEJJMrxy/urGeHo5aqpjDPkwKBgE3iOD6X1tSVhga8b5rWchlGO5nua/iaXXwId6QP536pmyAF0rOx257eIcobzyrPDlKK0UTh3xPpzayb/1/waDJhP1gKVLflp5pJcJ1qvpp5J8SvJhLJRaSBpvhOYlwI1mxLNKjtzQ6XFjXtWhC00tRmPm3YcXb9JC2WJdCTik8TAoGAJOAV6Q1ta6E5PN63uk2as0m7LQ+vIkyM5pz5VUshOxBP61OJrODYt1Q2/NFc13VaA47UZlIzvl1tSeuOfK+gwQSTCd2aidlFN0qhsEVEWLu9TYosJKZyJc1ueugzIc8Kie0U4s9QbZ3vZhT2FTMTwT1V8Tb/dGF6/lQufSyXd/sCgYEAoKoBySdweVP5LPqoAsWPlNpsVZQaKCwn41N2Z60LO+eWEPP95dKWF4tngOPL8qjIzetKSvrfx7zRX7HKZMWFYFd7cY+Ujla2s7pSIwE2k45aAcu0iudKAD93ONYTYWyERBrbi36oEAcJ+pyC9jUlRiC7SIWQR2Cw07iriultgWc=";
    // 服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://localhost:9003/mynotfiy";
    // 页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
    public static String return_url = "http://localhost:9003/myreturn";
    // 请求网关地址
    public static String URL = "https://openapi.alipaydev.com/gateway.do";
    // 编码
    public static String CHARSET = "UTF-8";
    // 返回格式
    public static String FORMAT = "json";
    // 支付宝公钥
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAugiI4mD8yuN6Ikt9/OTV2M0BC8frHqd1soVZp1/1+uZRjvgZm39dgpsCmaV4fh6X1tLFbpmxNwFHSzQL9258Lwk/QA08KVLcj15m+SnPFmJ7lK0pvExIx0Xyx0NGEvvcZC04d7R3jQ8ofarfJbGy1jSWLxZzvej4cbrUe6aNTw9ZATNJg/BNbY5A9skWwYXDRfSMml+jQG7z4INJptGB59+0MBCr8M3AN9XB5al1Kv8D3hahBMHEt+6ehTF/kQXX+/sbD5yKr0q57bkg5PucQVirRJ8d8OosizR3eEU8tHt2wE5ySY1emohCZiAD9n2z+uRtTWV2sxhotMk6W3vhfQIDAQAB";
    // 日志记录目录
    public static String log_path = "/log";
    // RSA2
    public static String SIGNTYPE = "RSA2";
}

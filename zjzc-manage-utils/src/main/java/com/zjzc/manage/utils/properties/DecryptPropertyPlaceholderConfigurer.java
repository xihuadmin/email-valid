package com.zjzc.manage.utils.properties;

import java.util.Enumeration;
import java.util.Properties;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import com.zjzc.manage.utils.encrypt.PropertiesEncoder;

public class DecryptPropertyPlaceholderConfigurer extends
        PropertyPlaceholderConfigurer {
	
    private PropertiesEncoder propertiesEncoder;


    public PropertiesEncoder getPropertiesEncoder() {
		return propertiesEncoder;
	}

	public void setPropertiesEncoder(PropertiesEncoder propertiesEncoder) {
		this.propertiesEncoder = propertiesEncoder;
	}

	@Override
    protected void convertProperties(Properties props) {
    	// 获取配置文件的key值
    	String key = props.getProperty("jdbc_pro_key", "jT#L505/pTb0%&d3&$u1R(#!9Fy3C");
    	
        Enumeration<?> propertyNames = props.propertyNames();
        while (propertyNames.hasMoreElements()) {
            String propertyName = (String) propertyNames.nextElement();
            
            if ("jdbc_username".equals(propertyName)
                    || "jdbc_password".equals(propertyName)) {
            	
                String propertyValue = props.getProperty(propertyName);
                if (propertyValue == null) {
                    propertyValue = "";
                }
                String decodedValue = propertiesEncoder.decode(propertyValue.trim(), key);
                
                props.setProperty(propertyName, decodedValue);
                
            }

        }
        
        super.convertProperties(props);
    }
}

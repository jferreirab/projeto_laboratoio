/**
 *      Copyright (c) 2009 União Atacado Distribuidor
 *      All Rights Reserved
 * 
 *      This product is protected by copyright and distributed under
 *      licenses restricting copying, distribution, and decompilation.
 * 
 *      Copyright (c) 2009 União Atacado Distribuidor
 *      Todos os direitos reservados
 *
 *      Este produto é protegido contra cópia e distribuição sobre
 *      restrição de licença de cópia, distribuição e descompilação.
 */
package com.util;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


/**
 *
 */
public class Sistema {

    private static String configPath = "c://opt//javaapp//config//compras-config.properties";


    public static Properties configSistema() throws IOException {

        return configSistema( configPath );

    }


    public static Properties configSistema( String configPath ) throws IOException {

        FileInputStream arquivoConfig = null;
        Properties configSistema = null;

        try {
            configSistema = new Properties();
            arquivoConfig = new FileInputStream( new File( configPath ) );
            configSistema.load( arquivoConfig );
        }
        finally {
            try {
                arquivoConfig.close();
            }
            catch ( IOException e ) {
                e.printStackTrace();
            }
        }
        return configSistema;
    }

}

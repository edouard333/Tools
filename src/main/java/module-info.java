/**
 * @author <a href="mailto:edouard128@hotmail.com">Edouard Jeanjean</a>
 */
module com.phenix.tools {
    requires jakarta.validation;
    requires java.desktop;
    requires java.sql;

    exports com.phenix.tools.businesslayer;
    exports com.phenix.tools.other;
}

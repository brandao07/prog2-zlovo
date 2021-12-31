module com.zlovo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires org.jetbrains.annotations;

    opens com.zlovo to javafx.fxml;
    exports com.zlovo;
    exports com.zlovo.dal.utilizador;
    opens com.zlovo.dal.utilizador to javafx.fxml;
    opens com.zlovo.dal to javafx.fxml;
    exports com.zlovo.dal.encomenda;
    exports com.zlovo.dal.empresa;
    exports com.zlovo.dal.empresa.enumerations;
    exports com.zlovo.dal.encomenda.enumerations;
    exports com.zlovo.dal;
    opens com.zlovo.gui to javafx.fxml;
    exports com.zlovo.gui;
    exports com.zlovo.gui.cliente;
    opens com.zlovo.gui.cliente to javafx.fxml;
    exports com.zlovo.gui.empresario;
    opens com.zlovo.gui.empresario to javafx.fxml;
    exports com.zlovo.gui.administrador;
    opens com.zlovo.gui.administrador to javafx.fxml;
    exports com.zlovo.gui.motard;
    opens com.zlovo.gui.motard to javafx.fxml;
}
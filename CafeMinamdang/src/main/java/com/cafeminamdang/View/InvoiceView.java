package com.cafeminamdang.View;

import com.cafeminamdang.Controller.InvoiceController;
import com.cafeminamdang.Model.Invoice;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class InvoiceView implements BaseView {
    private final InvoiceController invoiceController;
    private final BorderPane mainPanel;
    private VBox listView;
    private VBox detailView;
    private VBox formView;
    private ObservableList<Invoice> dataInvoice;
    private TableView<Invoice> tableView;

    public InvoiceView() {
        invoiceController = new InvoiceController();

        mainPanel = new BorderPane();
        mainPanel.setPrefSize(1000, 700);

        HBox header = createHeader();
        mainPanel.setTop(header);

        listView = createListView();
        detailView = createDetailView();
        formView = createFormView();

        mainPanel.setCenter(listView);

        HBox footer = createFooter();
        mainPanel.setBottom(footer);
    }

    @Override
    public Pane getRoot() {
        return mainPanel;
    }

    @Override
    public void refresh() {
        loadData();
        mainPanel.setCenter(listView);
    }

    private void loadData() {
        List<Invoice> invoices = invoiceController.getSpesifiedWhInvoice(ViewManager.getInstance().getIdGudang());
        dataInvoice = FXCollections.observableArrayList(invoices);
        tableView.setItems(dataInvoice);
    }

    private HBox createHeader() {
        HBox header = new HBox();
        header.setAlignment(Pos.CENTER_LEFT);
        header.setPadding(new Insets(15));
        header.setStyle("-fx-background-color: #E43A3A;");

        Label titleLabel = new Label("CafeMinamdang - Invoice");
        titleLabel.setFont(loadFont("Title"));
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        titleLabel.setTextFill(Color.WHITE);

        Node icon = createSvgIcon("M25.2387 4.16776L32.0431 6.71998L24.9957 9.4011L17.9483 6.71998L24.7527 4.16776C24.9089 4.10761 25.0825 4.10761 25.2474 4.16776H25.2387ZM11.8035 7.94024V17.582C11.6907 17.6163 11.5779 17.6507 11.465 17.6937L3.13314 20.8216C1.24978 21.5263 0 23.3223 0 25.316V35.5592C0 37.4669 1.13696 39.1942 2.90748 39.9676L11.2394 43.594C12.4892 44.1353 13.9038 44.1353 15.1536 43.594L24.9957 39.3059L34.8464 43.594C36.0962 44.1353 37.5109 44.1353 38.7606 43.594L47.0925 39.9676C48.8544 39.2028 50 37.4669 50 35.5592V25.3245C50 23.3223 48.7502 21.5349 46.8669 20.8216L38.535 17.6937C38.4222 17.6507 38.3093 17.6163 38.1965 17.582V7.94024C38.1965 5.93799 36.9467 4.15058 35.0634 3.43733L26.7315 0.30936C25.6206 -0.10312 24.3968 -0.10312 23.2859 0.30936L14.954 3.43733C13.0533 4.15058 11.8035 5.94658 11.8035 7.94024ZM34.0219 18.089L26.8703 20.7701V13.1048L34.0219 10.3893V18.089ZM13.4352 21.5521L20.2395 24.1043L13.1922 26.7768L6.14477 24.1043L12.9491 21.5521C13.1054 21.4919 13.2789 21.4919 13.4438 21.5521H13.4352ZM15.0668 39.1168V30.4805L22.2184 27.765V36.0061L15.0668 39.1168ZM36.5562 21.5521C36.7124 21.4919 36.886 21.4919 37.0509 21.5521L43.8552 24.1043L36.7992 26.7768L29.7518 24.1043L36.5562 21.5521ZM45.4088 36.1865L38.6738 39.1168V30.4805L45.8254 27.765V35.5592C45.8254 35.8342 45.6605 36.0748 45.4088 36.1865Z");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Button backButton = createIconButton("M17.7141 3.46406L23.4703 9.22031C23.8078 9.55781 24 10.0219 24 10.5C24 10.9781 23.8078 11.4422 23.4703 11.7797L17.7141 17.5359C17.4141 17.8359 17.0109 18 16.5891 18C15.7125 18 15 17.2875 15 16.4109V13.5H9C8.17031 13.5 7.5 12.8297 7.5 12V9C7.5 8.17031 8.17031 7.5 9 7.5H15V4.58906C15 3.7125 15.7125 3 16.5891 3C17.0109 3 17.4141 3.16875 17.7141 3.46406ZM7.5 3H4.5C3.67031 3 3 3.67031 3 4.5V16.5C3 17.3297 3.67031 18 4.5 18H7.5C8.32969 18 9 18.6703 9 19.5C9 20.3297 8.32969 21 7.5 21H4.5C2.01562 21 0 18.9844 0 16.5V4.5C0 2.01562 2.01562 0 4.5 0H7.5C8.32969 0 9 0.670312 9 1.5C9 2.32969 8.32969 3 7.5 3Z", 54, Color.WHITE,"#E43A3A", "#FF6B6B", "Logout");
        backButton.setOnAction(e -> {
            ViewManager.getInstance().setRole(null);
            ViewManager.getInstance().setIdGudang(0);
            ViewManager.getInstance().switchView("menu");
        });

        header.getChildren().addAll(icon, titleLabel, spacer, backButton);
        return header;
    }

    private VBox createListView() {
        VBox container = new VBox(10);
        container.setPadding(new Insets(20));

        HBox controlBar = new HBox(10);
        controlBar.setAlignment(Pos.CENTER_LEFT);

        Label titleLabel = new Label("Invoice List");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Button addButton = new Button("Add Invoice");
        addButton.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        addButton.setStyle("-fx-background-color: #E43A3A; -fx-text-fill: white;");
        addButton.setOnMouseEntered(e -> addButton.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: black;"));
        addButton.setOnAction(e -> showFormView(null));
        addButton.setOnMouseExited(e -> addButton.setStyle("-fx-background-color: #E43A3A; -fx-text-fill: white;"));
        controlBar.getChildren().addAll(titleLabel, spacer, addButton);

        tableView = new TableView<>();
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);

        TableColumn<Invoice, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("idInvoice"));

        TableColumn<Invoice, String> titleColumn = new TableColumn<>("Judul Invoice");
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("judulInvoice"));

        TableColumn<Invoice, String> dateColumn = new TableColumn<>("Tanggal");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("tanggal"));

        TableColumn<Invoice, Integer> gudangColumn = new TableColumn<>("ID Gudang");
        gudangColumn.setCellValueFactory(new PropertyValueFactory<>("idGudang"));

        TableColumn<Invoice, BigDecimal> unitPriceColumn = new TableColumn<>("Unit Price");
        unitPriceColumn.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));

        TableColumn<Invoice, Integer> qtyColumn = new TableColumn<>("Kuantitas");
        qtyColumn.setCellValueFactory(new PropertyValueFactory<>("kuantitas"));

        TableColumn<Invoice, String> totalColumn = new TableColumn<>("Total");
        totalColumn.setCellValueFactory(cellData -> javafx.beans.binding.Bindings.createStringBinding(
                () -> cellData.getValue().getHargaTotalInvoice().toString()));

        TableColumn<Invoice, Void> actionsColumn = new TableColumn<>("Actions");
        actionsColumn.setPrefWidth(180);
        actionsColumn.setCellFactory(param -> new TableCell<>() {
            private final Button viewBtn = new Button("View");
            private final Button editBtn = new Button("Edit");
            private final Button deleteBtn = new Button("Delete");
            private final HBox pane = new HBox(5, viewBtn, editBtn, deleteBtn);

            {
                viewBtn.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white;");
                editBtn.setStyle("-fx-background-color: #FFC107; -fx-text-fill: white;");
                deleteBtn.setStyle("-fx-background-color: #F44336; -fx-text-fill: white;");

                viewBtn.setOnAction(event -> {
                    Invoice invoice = getTableView().getItems().get(getIndex());
                    showDetailView(invoice);
                });

                editBtn.setOnAction(event -> {
                    Invoice invoice = getTableView().getItems().get(getIndex());
                    showFormView(invoice);
                });

                deleteBtn.setOnAction(event -> {
                    Invoice invoice = getTableView().getItems().get(getIndex());
                    deleteInvoice(invoice);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : pane);
            }
        });

        tableView.getColumns().addAll(idColumn, titleColumn, dateColumn, gudangColumn, unitPriceColumn, qtyColumn, totalColumn, actionsColumn);

        loadData();

        container.getChildren().addAll(controlBar, tableView);
        VBox.setVgrow(tableView, Priority.ALWAYS);
        return container;
    }

    private VBox createDetailView() {
        VBox container = new VBox(20);
        container.setPadding(new Insets(20));
        return container;
    }

    private VBox createFormView() {
        VBox container = new VBox(20);
        container.setPadding(new Insets(20));

        Label formTitle = new Label("Add New Invoice");
        formTitle.setFont(Font.font("Arial", FontWeight.BOLD, 18));

        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(10);
        form.setPadding(new Insets(20, 0, 0, 0));

        Label titleLabel = new Label("Judul Invoice:");
        TextField titleField = new TextField();
        titleField.setPrefWidth(300);

        Label dateLabel = new Label("Tanggal (YYYY-MM-DD):");
        TextField dateField = new TextField();

        Spinner<Integer> gudangSpinner = new Spinner<>(0, Integer.MAX_VALUE, 0);
        gudangSpinner.setEditable(true);
        gudangSpinner.setPrefWidth(300);

        Label unitPriceLabel = new Label("Unit Price:");
        TextField unitPriceField = new TextField();

        Label qtyLabel = new Label("Kuantitas:");
        Spinner<Integer> qtySpinner = new Spinner<>(0, Integer.MAX_VALUE, 0);
        qtySpinner.setEditable(true);
        qtySpinner.setPrefWidth(300);

        form.add(titleLabel, 0, 0);
        form.add(titleField, 1, 0);
        form.add(dateLabel, 0, 1);
        form.add(dateField, 1, 1);
        form.add(unitPriceLabel, 0, 3);
        form.add(unitPriceField, 1, 3);
        form.add(qtyLabel, 0, 4);
        form.add(qtySpinner, 1, 4);

        HBox buttonBar = new HBox(10);
        buttonBar.setAlignment(Pos.CENTER_RIGHT);
        buttonBar.setPadding(new Insets(20, 0, 0, 0));

        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(e -> showListView());

        Button saveButton = new Button("Save");
        saveButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill:white;");
        saveButton.setOnAction(e -> {
            Invoice invoice = (Invoice) form.getUserData();
            try {
                String judul = titleField.getText();
                String tanggal = dateField.getText();
                int idGudang = ViewManager.getInstance().getIdGudang();
                BigDecimal unitPrice = new BigDecimal(unitPriceField.getText());
                int kuantitas = qtySpinner.getValue();

                if (invoice == null) {
                    invoice = invoiceController.createInvoice(judul, tanggal, idGudang, unitPrice, kuantitas);
                } else {
                    invoice = invoiceController.updateInvoice(invoice, judul, tanggal, idGudang, unitPrice, kuantitas);
                }

                boolean success = invoiceController.saveInvoice(invoice);

                if (success) {
                    showListView();
                    loadData();
                } else {
                    showAlert("Error", "Failed to save invoice!");
                }
            } catch (Exception ex) {
                showAlert("Error", "Invalid input: Please enter the format as specified");
            }
        });

        buttonBar.getChildren().addAll(cancelButton, saveButton);

        container.getChildren().addAll(formTitle, form, buttonBar);
        return container;
    }

    private void deleteInvoice(Invoice invoice) {
        Alert confirmDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmDialog.setTitle("Delete Invoice");
        confirmDialog.setHeaderText("Delete invoice #" + invoice.getIdInvoice() + "?");
        confirmDialog.setContentText("This action cannot be undone.");

        DialogPane dialogPane = confirmDialog.getDialogPane();
        dialogPane.setStyle("-fx-background-color: #F8F8F8;-fx-font-family: 'Arial';");

        Optional<ButtonType> result = confirmDialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            boolean success = invoiceController.deleteInvoice(invoice);
            if (success) {
                loadData();
            } else {
                showAlert("Error", "Failed to delete invoice!");
            }
        }
    }

    private HBox createFooter() {
        HBox footer = new HBox();
        footer.setAlignment(Pos.CENTER);
        footer.setPadding(new Insets(10));
        footer.setStyle("-fx-background-color: #E43A3A;");

        Label copyright = new Label("\u00a9 2025 Cafe Minamdang. All rights reserved.");
        copyright.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 12));
        copyright.setTextFill(Color.WHITE);

        footer.getChildren().add(copyright);
        return footer;
    }

    private void showListView() {
        mainPanel.setCenter(listView);
    }

    private void showDetailView(Invoice invoice) {
        detailView.getChildren().clear();

        Label titleLabel = new Label("Invoice #" + invoice.getIdInvoice());
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        VBox details = new VBox(15);
        details.setPadding(new Insets(20, 0, 0, 0));

        details.getChildren().addAll(
            createDetailLabel("Judul Invoice", invoice.getJudulInvoice()),
            createDetailLabel("Tanggal", invoice.getTanggal()),
            createDetailLabel("ID Gudang", String.valueOf(invoice.getIdGudang())),
            createDetailLabel("Unit Price", invoice.getUnitPrice().toString()),
            createDetailLabel("Kuantitas", String.valueOf(invoice.getKuantitas())),
            createDetailLabel("Total", invoice.getHargaTotalInvoice().toString())
        );

        HBox buttonBar = new HBox(10);
        buttonBar.setAlignment(Pos.CENTER_RIGHT);
        buttonBar.setPadding(new Insets(20, 0, 0, 0));

        Button backButton = new Button("Back to Invoice List");
        backButton.setOnAction(e -> showListView());

        Button editButton = new Button("Edit Invoice");
        editButton.setStyle("-fx-background-color: #FFC107; -fx-text-fill:white;");
        editButton.setOnAction(e -> showFormView(invoice));

        buttonBar.getChildren().addAll(backButton, editButton);

        detailView.getChildren().addAll(titleLabel, details, buttonBar);
        mainPanel.setCenter(detailView);
    }

    private Node createDetailLabel(String title, String content) {
        VBox box = new VBox(2);
        Label labelTitle = new Label(title);
        labelTitle.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        Label labelContent = new Label(content);
        labelContent.setWrapText(true);
        box.getChildren().addAll(labelTitle, labelContent);
        return box;
    }

    private void showFormView(Invoice invoice) {
        GridPane form = (GridPane) formView.getChildren().get(1);
        Label formTitle = (Label) formView.getChildren().get(0);

        form.setUserData(invoice);

        TextField titleField = (TextField) form.getChildren().get(1);
        TextField dateField = (TextField) form.getChildren().get(3);
        TextField unitPriceField = (TextField) form.getChildren().get(5);

        if (invoice == null) {
            formTitle.setText("Add New Invoice");
            titleField.clear();
            dateField.clear();
            unitPriceField.clear();
        } else {
            formTitle.setText("Edit Invoice");
            titleField.setText(invoice.getJudulInvoice());
            dateField.setText(invoice.getTanggal());
            unitPriceField.setText(invoice.getUnitPrice().toString());
        }

        mainPanel.setCenter(formView);
    }

    private Node createSvgIcon(String svgPath) {
        SVGPath path = new SVGPath();
        path.setContent(svgPath);
        path.setFill(Color.WHITE);

        path.setScaleX(0.5);
        path.setScaleY(0.5);

        return path;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(title);
        alert.setContentText(message);

        DialogPane dialogPane = alert.getDialogPane();
        Label headerLabel = (Label) dialogPane.lookup(".header-panel .label");
        if (headerLabel != null) {
            headerLabel.setFont(loadFont("Thin-SemiBold"));
            headerLabel.setTextFill(Color.WHITE);
        }
        Label contentLabel = (Label) dialogPane.lookup(".content.label");
        if (contentLabel != null) {
            contentLabel.setFont(loadFont("Thin-SemiBold"));
        }
        alert.showAndWait();
    }

    private Font loadFont(String Type) {
        String basePath = "/fonts/";
        switch (Type) {
            case "Title":
                try {
                    Font poppins = Font.loadFont(getClass().getResource(basePath + "Poppins-SemiBold.ttf").toExternalForm(), 24);
                    return poppins != null ? poppins : Font.getDefault();
                } catch (Exception e) {
                    e.printStackTrace();
                    return new Font("System", 24);
                }
            case "Thin-SemiBold":
                try {
                    Font poppins = Font.loadFont(getClass().getResource(basePath + "Poppins-SemiBold.ttf").toExternalForm(), 10);
                    return poppins != null ? poppins : Font.getDefault();
                } catch (Exception e) {
                    e.printStackTrace();
                    return new Font("System", 16);
                }
            default:
                try {
                    Font poppins = Font.loadFont(getClass().getResource(basePath + "Poppins-Regular.ttf").toExternalForm(), 10);
                    return poppins != null ? poppins : Font.getDefault();
                } catch (Exception e) {
                    e.printStackTrace();
                    return new Font("System", 10);
                }
        }
    }

    private Button createIconButton(String svgPath, double size, Color iconColor, String btnColor, String hoverColor, String text) {
        SVGPath path = new SVGPath();
        path.setContent(svgPath);
        path.setFill(iconColor);

        StackPane iconContainer = new StackPane();
        iconContainer.getChildren().add(path);

        Button button = new Button(text);
        button.setFont(loadFont("Thin-SemiBold"));
        button.setTextFill(iconColor);
        button.setGraphic(iconContainer);
        button.setGraphicTextGap(10);
        String buttonStyle =
                "-fx-background-color: " + btnColor + ";" +
                        "-fx-min-height: " + size + "px;" +
                        "-fx-padding: 0 15px 0 15px;";

        button.setStyle(buttonStyle);

        String hoverStyle =
                "-fx-background-color: " + hoverColor + ";" +
                        "-fx-min-height: " + size + "px;" +
                        "-fx-padding: 0 15px 0 15px;" +
                        "-fx-cursor: hand;";

        button.setOnMouseEntered(e -> button.setStyle(hoverStyle));
        button.setOnMouseExited(e -> button.setStyle(buttonStyle));

        return button;
    }
}
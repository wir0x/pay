package ch.swissbytes.ewallet.service;

import ch.swissbytes.ewallet.tigomoney.action.TigoMoneyServiceImpl;
import ch.swissbytes.ewallet.tigomoney.dto.*;
import junit.framework.TestCase;
//import org.apache.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class EWalletServiceTest extends TestCase {

    private final static Logger log = LogManager.getLogger(EWalletServiceTest.class);

    private EWalletService eWalletService;

    @Before
    public void setUp() throws Exception {
        TMConnectionDto tmConnectionDto = TMConnectionDto.createNew();
        eWalletService = new TigoMoneyServiceImpl(tmConnectionDto);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testRequestPayment() throws Exception {
        TMPaymentRequestDto payment = new TMPaymentRequestDto();
        payment.setName("ElianaZuruguay");//Ej: Juan Perez
        payment.setCorrectUrl("http://www.sincuentos.com");//Ej: https://app.buho.bo/success
        payment.setErrorUrl("http://www.sincuentos.com");//Ej: https://app.buho.bo/error

        payment.setNotification("SinCuentos");//Ej: Confirma que desea pagar Bs. 135.00 |a BUHO Tracking| Ingrese su PIN para confirmar.
        payment.setConfirmation("SinCuentos");//Ej: BUHO Gracias por su compra");
        payment.setMessage("Suscripción a servicio BUHO - 30 dias");//Prueba de pago - " + (new Date()).toString());

        payment.setOrderId("14784");//orderId);
        payment.setLine(77667549);//Ruben: 78456679; Jose Carlos: 78160010;
        payment.setDocumentNumber("4719986");//Ruben: 4719986; Jose Carlos: 6322533;

        payment.setBusinessName("SwissBytes Ltda.");//Ej: SwissBytes Ltda.
        payment.setNit("149386022");//Ej: 149386022

        payment.setAmount(30.0);//11.75d);

//        List<TMPaymentItemDto> items = new ArrayList();
//        items.add(TMPaymentItemDto.createNew("1", 1, "BUHO plan de 30 dias", 1d, 1d));//"1",6,"Plato de porcelana",1.5d, 9d));
////        items.add(TMPaymentItemDto.createNew("2", 1, "Kit de aseo personal", 2.75d, 2.75d));
//        payment.setItems(items);

        TMPaymentResponseDto paymentResponse = eWalletService.requestPayment(payment);
        log.info("Result orderId: " + paymentResponse.getOrderId());
        log.info("Result code: " + paymentResponse.getCode());
        log.info("Result message: " + paymentResponse.getMessage());
        log.info("Result description: " + paymentResponse.getDescription());
        assertEquals("0", String.valueOf(paymentResponse.getCode()));
    }

//    @Test
//    public void testCheckStatus() throws Exception {
//        TMStatusResponseDto statusResponse = eWalletService.checkStatus("1");
//        log.info("Status code: " + statusResponse.getCode());
//        log.info("Status url: " + statusResponse.getUrl());
//        log.info("Status description: " + statusResponse.getDescription());
//        assertEquals("1", String.valueOf(statusResponse.getCode()));
//    }

    @Test
    public void testHasConnection() throws Exception {
        boolean hasConnection = eWalletService.hasConnection();
        log.info("has connection " + hasConnection);
        assertTrue(hasConnection);
    }
}
//package ch.swissbytes.ewallet.service;
//
//import ch.swissbytes.ewallet.tigomoney.action.TigoMoneyServiceImpl;
//import ch.swissbytes.ewallet.tigomoney.dto.*;
//import junit.framework.TestCase;
////import org.apache.log4j.Logger;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//public class EWalletServiceTest extends TestCase {
//
//    private final static Logger log = LogManager.getLogger(EWalletServiceTest.class);
//
//
//    @Test
//    public void testRequestPayment() throws Exception {
//        assertEquals("0", "0");
//
//    }
//
//
////    private EWalletService eWalletService;
////
////    @Before
////    public void setUp() throws Exception {
////        TMConnectionDto tmConnectionDto = TMConnectionDto.createNew();
////        eWalletService = new TigoMoneyServiceImpl(tmConnectionDto);
////    }
////
////    @After
////    public void tearDown() throws Exception {
////
////    }
////
////    @Test
////    @Deprecated
////    public void testRequestPayment() throws Exception {
////        TMPaymentRequestDto payment = new TMPaymentRequestDto();
////        payment.setName("Eliana Zuruguay");//Ej: Juan Perez
////        payment.setCorrectUrl("http://www.sincuentos.com/payment/sucess");//Ej: https://app.buho.bo/success
////        payment.setErrorUrl("http://www.sincuentos.com/payment/error");//Ej: https://app.buho.bo/error
////
////        payment.setNotification(" por 1 cupones SinCuentos de Â¡Precio especial en 1/4 de Pollo! .Gracias por utilizar SinCuentos");//Ej: Confirma que desea pagar Bs. 135.00 |a BUHO Tracking| Ingrese su PIN para confirmar.
////        payment.setConfirmation("por 1 cupones SinCuentos de Â¡Precio especial en 1/4 de Pollo!");//Ej: BUHO Gracias por su compra");
////        payment.setMessage("Suscripción a servicio BUHO - 30 dias");//Prueba de pago - " + (new Date()).toString());
////
////        payment.setOrderId("4488869");//orderId);
////        payment.setLine(77667549);//Ruben: 78456679; Jose Carlos: 78160010;
////        payment.setDocumentNumber("7918446");//Ruben: 4719986; Jose Carlos: 6322533;
////
////        payment.setBusinessName("SwissBytes Ltda.");//Ej: SwissBytes Ltda.
////        payment.setNit("149386022");//Ej: 149386022
////
////        payment.setAmount(1.0);//11.75d);
////
////        List<TMPaymentItemDto> items = new ArrayList();
////        items.add(TMPaymentItemDto.createNew("1", 1, "¡Precio especial en 1/4 de Pollo!",1.0,1.0));
////
////        //"1",6,"Plato de porcelana",1.5d, 9d));
//////        items.add(TMPaymentItemDto.createNew("2", 1, "Kit de aseo personal", 2.75d, 2.75d));
////        payment.setItems(items);
////
////        TMPaymentResponseDto paymentResponse = eWalletService.requestPayment(payment);
////        log.info("Result orderId: " + paymentResponse.getOrderId());
////        log.info("Result code: " + paymentResponse.getCode());
////        log.info("Result message: " + paymentResponse.getMessage());
////        log.info("Result description: " + paymentResponse.getDescription());
////        assertEquals("0", String.valueOf(paymentResponse.getCode()));
////    }
////
////
//////    @Test
//////    public void testRequestPaymentProductionSinCuentos() throws Exception {
//////        log.info("###################################  SINCUENTOS PRODUCTION");
////////        TMPaymentRequestDto payment = new TMPaymentRequestDto();
////////        // setting production data
////////        TMConnectionDto tmConnectionDto = new TMConnectionDto();
////////        tmConnectionDto.setUrl("https://vipagos.com.bo/PasarelaServices/CustomerServices?wsdl");
////////        tmConnectionDto.setEncryptionKey("1HOOSUZVXJXLMS4KCH42STEY");
////////        tmConnectionDto.setIdKey("0ffbd6227bc93ad8fab4f152ad381432a610085cb5407ce1ad5fe12ff1a44bf6527903e24c1cf0577b4bc730a604f201980b650e111cb2532a54b2ddf68f5bdd");
////////
////////        eWalletService = new TigoMoneyServiceImpl(tmConnectionDto);
////////
////////
////////        payment.setName("Eliana Zuruguay");//Ej: Juan Perez
////////        payment.setCorrectUrl("https://app.buho.bo/success");//Ej: https://app.buho.bo/success
////////        payment.setErrorUrl("https://app.buho.bo/success");//Ej: https://app.buho.bo/error
////////
////////        payment.setNotification(" por 1 cupones SinCuentos de Â¡Precio especial en 1/4 de Pollo! .Gracias por utilizar SinCuentos");//Ej: Confirma que desea pagar Bs. 135.00 |a BUHO Tracking| Ingrese su PIN para confirmar.
////////        payment.setConfirmation("por 1 cupones SinCuentos de Â¡Precio especial en 1/4 de Pollo!");//Ej: BUHO Gracias por su compra");
////////        payment.setMessage("Suscripción a servicio BUHO - 30 dias");//Prueba de pago - " + (new Date()).toString());
////////
////////        payment.setOrderId("156");//orderId);
////////        payment.setLine(77355967);//Ruben: 78456679; Jose Carlos: 78160010;
////////        payment.setDocumentNumber("7918446");//Ruben: 4719986; Jose Carlos: 6322533;
////////
////////        payment.setBusinessName("SwissBytes Ltda.");//Ej: SwissBytes Ltda.
////////        payment.setNit("149386022");//Ej: 149386022
////////
////////        payment.setAmount(1.0);//11.75d);
////////
////////        List<TMPaymentItemDto> items = new ArrayList();
////////        items.add(TMPaymentItemDto.createNew("1", 1, "Test",1.0,1.0));
////////        payment.setItems(items);
////////
////////        TMPaymentResponseDto paymentResponse = eWalletService.requestPayment(payment);
////////        log.info("Result orderId: " + paymentResponse.getOrderId());
////////        log.info("Result code: " + paymentResponse.getCode());
////////        log.info("Result message: " + paymentResponse.getMessage());
////////        log.info("Result description: " + paymentResponse.getDescription());
////////        assertEquals("0", String.valueOf(paymentResponse.getCode()));
//////
//////            TMPaymentRequestDto payment = new TMPaymentRequestDto();
//////
//////            TMConnectionDto tmConnectionDto = new TMConnectionDto();
////////            tmConnectionDto.setUrl("https://vipagos.com.bo/PasarelaServices/CustomerServices?wsdl");
////////            tmConnectionDto.setEncryptionKey("89A50THU7GVOQD8UH83F52UB");
////////            tmConnectionDto.setIdKey("a8003c05f35770d5e1b35ff3983d7fed1d1e024bce7de6891b46bfd726d8d0789e37bd4d442ff5bf8077e187cf4320abb1810a8c84ff1f4354ab33fff6b1547f");
//////            eWalletService = new TigoMoneyServiceImpl(tmConnectionDto);
//////
//////            payment.setName("Ruben Dario Lopez Cairo");//Ej: Juan Perez
//////            payment.setCorrectUrl("http://test.buho.bo");//Ej: https://app.buho.bo/success
//////            payment.setErrorUrl("http://www.bing.com");//Ej: https://app.buho.bo/error
//////
//////            payment.setNotification("Buho's: Suscripción");//Ej: Confirma que desea pagar Bs. 135.00 |a BUHO Tracking| Ingrese su PIN para confirmar.
//////            payment.setConfirmation("BUHO Pago de plan");//Ej: BUHO Gracias por su compra");
//////            payment.setMessage("Suscripción a servicio BUHO - 30 dias");//Prueba de pago - " + (new Date()).toString());
//////
//////            payment.setOrderId("1125258");//orderId);
//////            payment.setLine(78456679);//Ruben: 78456679; Jose Carlos: 78160010;
//////            payment.setDocumentNumber("4719986");//Ruben: 4719986; Jose Carlos: 6322533;
//////
//////            payment.setBusinessName("SwissBytes Ltda.");//Ej: SwissBytes Ltda.
//////            payment.setNit("149386022");//Ej: 149386022
//////
//////            payment.setAmount(1.0);//11.75d);
//////
//////            List<TMPaymentItemDto> items = new ArrayList();
//////            items.add(TMPaymentItemDto.createNew("1", 1, "BUHO plan de 30 dias", 1.0, 1.0));//"1",6,"Plato de porcelana",1.5d, 9d));
////////        items.add(TMPaymentItemDto.createNew("2", 1, "Kit de aseo personal", 2.75d, 2.75d));
//////            payment.setItems(items);
//////
//////            TMPaymentResponseDto paymentResponse = eWalletService.requestPayment(payment);
//////            log.info("Result orderId: " + paymentResponse.getOrderId());
//////            log.info("Result code: " + paymentResponse.getCode());
//////            log.info("Result message: " + paymentResponse.getMessage());
//////            log.info("Result description: " + paymentResponse.getDescription());
//////            assertEquals("0", String.valueOf(paymentResponse.getCode()));
//////
//////    }
//////
//////
//////    @Test
//////    public void testCheckStatus() throws Exception {
//////        TMStatusResponseDto statusResponse = eWalletService.checkStatus("1");
//////        log.info("Status code: " + statusResponse.getCode());
//////        log.info("Status url: " + statusResponse.getUrl());
//////        log.info("Status description: " + statusResponse.getDescription());
//////        assertEquals("1", String.valueOf(statusResponse.getCode()));
//////    }
//////
//////    @Test
//////    public void testHasConnection() throws Exception {
//////        boolean hasConnection = eWalletService.hasConnection();
//////        log.info("has connection " + hasConnection);
//////        assertTrue(hasConnection);
//////    }
//}
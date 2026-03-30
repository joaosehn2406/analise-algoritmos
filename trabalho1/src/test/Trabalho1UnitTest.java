package test;

import adapter.ArCondicionadoVentoBaumAdapter;
import adapter.LampadaPhellipesAdapter;
import adapter.LampadaShoyuMiAdapter;
import adapter.PersianaNatLightAdapter;
import adapter.PersianaSolariusAdapter;
import model.ArCondicionadoGellaKaza;
import model.ArCondicionadoVentoBaumn;
import model.LampadaPhellipes;
import model.LampadaShoyuMi;
import model.PersianaNatLight;
import model.PersianaSolarius;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Trabalho1UnitTest {

    @Test
    void arCondicionadoGellaKaza_DeveIniciarDesligadoComTemperaturaPadrao() {
        ArCondicionadoGellaKaza ar = new ArCondicionadoGellaKaza();

        assertFalse(ar.estaLigado());
        assertEquals(28, ar.getTemperatura());
    }

    @Test
    void arCondicionadoGellaKaza_DeveAtivarEDesativar() {
        ArCondicionadoGellaKaza ar = new ArCondicionadoGellaKaza();

        ar.ativar();
        assertTrue(ar.estaLigado());

        ar.desativar();
        assertFalse(ar.estaLigado());
    }

    @Test
    void arCondicionadoGellaKaza_DeveAumentarEDiminuirTemperaturaNosLimites() {
        ArCondicionadoGellaKaza ar = new ArCondicionadoGellaKaza();

        ar.aumentarTemperatura();
        assertEquals(29, ar.getTemperatura());

        ar.diminuirTemperatura();
        assertEquals(28, ar.getTemperatura());
    }

    @Test
    void arCondicionadoGellaKaza_DeveLancarExcecaoAoUltrapassarLimiteMaximo() {
        ArCondicionadoGellaKaza ar = new ArCondicionadoGellaKaza();

        while (ar.getTemperatura() < 35) {
            ar.aumentarTemperatura();
        }

        assertThrows(IllegalArgumentException.class, ar::aumentarTemperatura);
    }

    @Test
    void arCondicionadoGellaKaza_DeveLancarExcecaoAoUltrapassarLimiteMinimo() {
        ArCondicionadoGellaKaza ar = new ArCondicionadoGellaKaza();

        while (ar.getTemperatura() > 15) {
            ar.diminuirTemperatura();
        }

        assertThrows(IllegalArgumentException.class, ar::diminuirTemperatura);
    }

    @Test
    void arCondicionadoVentoBaumn_DeveExigirLigadoParaDefinirTemperatura() {
        ArCondicionadoVentoBaumn ar = new ArCondicionadoVentoBaumn();

        assertThrows(IllegalArgumentException.class, () -> ar.definirTemperatura(25));
    }

    @Test
    void arCondicionadoVentoBaumn_DeveRespeitarFaixaDeTemperatura() {
        ArCondicionadoVentoBaumn ar = new ArCondicionadoVentoBaumn();
        ar.ligar();

        assertThrows(IllegalArgumentException.class, () -> ar.definirTemperatura(14));
        assertThrows(IllegalArgumentException.class, () -> ar.definirTemperatura(36));
    }

    @Test
    void arCondicionadoVentoBaumn_DeveDefinirTemperaturaValidaQuandoLigado() {
        ArCondicionadoVentoBaumn ar = new ArCondicionadoVentoBaumn();
        ar.ligar();

        ar.definirTemperatura(30);

        assertEquals(30, ar.getTemperatura());
    }

    @Test
    void arCondicionadoVentoBaumAdapter_DeveLancarExcecaoSeModeloForNulo() {
        assertThrows(IllegalArgumentException.class, () -> new ArCondicionadoVentoBaumAdapter(null));
    }

    @Test
    void arCondicionadoVentoBaumAdapter_DeveLigarDesligarEAjustarTemperatura() {
        ArCondicionadoVentoBaumn model = new ArCondicionadoVentoBaumn();
        ArCondicionadoVentoBaumAdapter adapter = new ArCondicionadoVentoBaumAdapter(model);

        adapter.ligar();
        assertTrue(model.estaLigado());

        adapter.definirTemperatura(26);
        assertEquals(26, model.getTemperatura());

        adapter.aumentarTemperatura();
        assertEquals(27, model.getTemperatura());

        adapter.diminuirTemperatura();
        assertEquals(26, model.getTemperatura());

        adapter.desligar();
        assertFalse(model.estaLigado());
    }

    @Test
    void lampadaPhellipes_DeveValidarFaixaDeIntensidade() {
        LampadaPhellipes lampada = new LampadaPhellipes();

        assertThrows(IllegalArgumentException.class, () -> lampada.setIntensidade(-1));
        assertThrows(IllegalArgumentException.class, () -> lampada.setIntensidade(101));
    }

    @Test
    void lampadaPhellipesAdapter_DeveLigarEDesligarPorIntensidade() {
        LampadaPhellipes lampada = new LampadaPhellipes();
        LampadaPhellipesAdapter adapter = new LampadaPhellipesAdapter(lampada);

        adapter.ligar();
        assertEquals(100, lampada.getIntensidade());

        adapter.desligar();
        assertEquals(0, lampada.getIntensidade());
    }

    @Test
    void lampadaPhellipesAdapter_DeveLancarExcecaoSeModeloForNulo() {
        assertThrows(IllegalArgumentException.class, () -> new LampadaPhellipesAdapter(null));
    }

    @Test
    void lampadaShoyuMi_DeveLigarEDesligar() {
        LampadaShoyuMi lampada = new LampadaShoyuMi();

        assertFalse(lampada.estaLigada());

        lampada.ligar();
        assertTrue(lampada.estaLigada());

        lampada.desligar();
        assertFalse(lampada.estaLigada());
    }

    @Test
    void lampadaShoyuMiAdapter_DeveControlarEstadoDaLampada() {
        LampadaShoyuMi lampada = new LampadaShoyuMi();
        LampadaShoyuMiAdapter adapter = new LampadaShoyuMiAdapter(lampada);

        adapter.ligar();
        assertTrue(lampada.estaLigada());

        adapter.desligar();
        assertFalse(lampada.estaLigada());
    }

    @Test
    void lampadaShoyuMiAdapter_DeveLancarExcecaoSeModeloForNulo() {
        assertThrows(IllegalArgumentException.class, () -> new LampadaShoyuMiAdapter(null));
    }

    @Test
    void persianaNatLight_DeveValidarRegrasDeAberturaEFechamento() throws Exception {
        PersianaNatLight persiana = new PersianaNatLight();

        assertTrue(persiana.estaPalhetaAberta());
        assertTrue(persiana.estaPalhetaErguida());

        persiana.descerPalheta();
        persiana.fecharPalheta();
        assertFalse(persiana.estaPalhetaAberta());

        assertThrows(Exception.class, persiana::subirPalheta);
    }

    @Test
    void persianaNatLightAdapter_DeveAbrirESubirAoAbrir() throws Exception {
        PersianaNatLight persiana = new PersianaNatLight();
        persiana.descerPalheta();
        persiana.fecharPalheta();

        PersianaNatLightAdapter adapter = new PersianaNatLightAdapter(persiana);
        adapter.abrir();

        assertTrue(persiana.estaPalhetaAberta());
        assertTrue(persiana.estaPalhetaErguida());
    }

    @Test
    void persianaNatLightAdapter_DeveDescerEFecharAoFechar() {
        PersianaNatLight persiana = new PersianaNatLight();
        PersianaNatLightAdapter adapter = new PersianaNatLightAdapter(persiana);

        adapter.fechar();

        assertFalse(persiana.estaPalhetaErguida());
        assertFalse(persiana.estaPalhetaAberta());
    }

    @Test
    void persianaNatLightAdapter_DeveLancarExcecaoSeModeloForNulo() {
        assertThrows(IllegalArgumentException.class, () -> new PersianaNatLightAdapter(null));
    }

    @Test
    void persianaSolarius_DeveSubirEDescer() {
        PersianaSolarius persiana = new PersianaSolarius();

        assertTrue(persiana.estaAberta());

        persiana.descerPersiana();
        assertFalse(persiana.estaAberta());

        persiana.subirPersiana();
        assertTrue(persiana.estaAberta());
    }

    @Test
    void persianaSolariusAdapter_DeveAbrirEFechar() {
        PersianaSolarius persiana = new PersianaSolarius();
        PersianaSolariusAdapter adapter = new PersianaSolariusAdapter(persiana);

        adapter.fechar();
        assertFalse(persiana.estaAberta());

        adapter.abrir();
        assertTrue(persiana.estaAberta());
    }

    @Test
    void persianaSolariusAdapter_DeveLancarExcecaoSeModeloForNulo() {
        assertThrows(IllegalArgumentException.class, () -> new PersianaSolariusAdapter(null));
    }
}

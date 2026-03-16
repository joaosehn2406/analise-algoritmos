package factory;

import model.EntregaEncomendaPac;
import model.EntregaRetiradaEmLoja;
import model.EntregaSedex;
import model.TipoEntrega;
import model.interfaces.ITipoEntrega;

public class TipoDeFreteFactory {
    
    public static ITipoEntrega criar(TipoEntrega tipo) {
        return switch (tipo) {
            case ENCOMENDA_PAC -> new EntregaEncomendaPac();
            case SEDEX -> new EntregaSedex();
            case RETIRADA_LOJA -> new EntregaRetiradaEmLoja();
        };
    }
}

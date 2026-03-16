package model;

import model.interfaces.ITipoEntrega;

public class EstrategiaFreteFactory {
    
    public static ITipoEntrega criar(TipoEntrega tipo) {
        return switch (tipo) {
            case ENCOMENDA_PAC -> new EntregaEncomendaPac();
            case SEDEX -> new EntregaSedex();
            case RETIRADA_LOJA -> new EntregaRetiradaEmLoja();
        };
    }
}

package next.school.cesar.desafiospring.response;

public class EntityRegisterResponse<T> {
    public static final int STATUS_OK = 0;
    public static final int STATUS_ENTIDADE_NAO_ENCONTRADA = 1;
    public static final int STATUS_DADO_INVALIDO = 2;
    public static final int STATUS_ENTIDADE_EXISTENTE = 3;
    private int statusProcessamento = STATUS_OK;
    private String mensagemErro = null;
    private T entidade;

    public EntityRegisterResponse(int statusProcessamento, String mensagemErro) {
        super();
        this.mensagemErro = mensagemErro;
        this.statusProcessamento = statusProcessamento;
    }
    public EntityRegisterResponse(T entidade) {
        this.entidade = entidade;
    }
    public String getMensagemErro() {
        return mensagemErro;
    }
    public T getEntidade() {
        return entidade;
    }
    public int getStatusProcessamento() {
        return statusProcessamento;
    }
}

package model;

public class Client {

    private int id;
    private String nome;
    private String endereco;
    private String celular;
    private String email;
    private String cpf;

    public Client(){
        this(null, null, null, null, null, null);
    }

    public Client(int id,String nome,String endereco,String celular,String email,String cpf){
        
        setId(id);
        setNome(nome);
        setEndereco(endereco);
        setCelular(celular);
        setEmail(email);
        setCpf(cpf);

    }

    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getNome(){
        return this.nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getEndereco(){
        return this.endereco;
    }

    public void setEndereco(String endereco){
        this.endereco = endereco;
    }

    public String getCelular(){
        return this.celular;
    }

    public void setCelular(String celular){
        this.celular = celular;
    }

    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getCpf(){
        return this.cpf;
    }

    public void setCpf(String cpf){
        this.cpf = cpf;
    }

    //TODO Implement interface I_Json

    //Override da Classe Object

    @Override
	public boolean equals(Object obj) {
		return (this.getId() == ((Client) obj).getId());
	}
}

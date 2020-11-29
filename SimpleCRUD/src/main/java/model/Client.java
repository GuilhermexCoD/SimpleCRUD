package model;

import org.json.JSONObject;

import Interfaces.I_Json;

public class Client implements I_Json{

    private String id;
    private String nome;
    private String endereco;
    private String celular;
    private String email;
    private String cpf;

    public Client(){
        this(null, null, null, null, null, null);
    }

    public Client(String id,String nome,String endereco,String celular,String email,String cpf){
        
        setId(id);
        setNome(nome);
        setEndereco(endereco);
        setCelular(celular);
        setEmail(email);
        setCpf(cpf);

    }

    public String getId(){
        return this.id;
    }

    public void setId(String id){
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

    //I_Json Interface
    @Override
    public JSONObject toJson(){
        JSONObject obj = new JSONObject();
		obj.put("id", this.getId());
		obj.put("nome", this.getNome());
		obj.put("endereco", this.getEndereco());
		obj.put("celular", this.getCelular());
		obj.put("email", this.getEmail());
		obj.put("cpf", this.getCpf());
		return obj;
    }
    //Override da Classe Object

    @Override
	public boolean equals(Object obj) {
		return (this.getId() == ((Client) obj).getId());
	}
}

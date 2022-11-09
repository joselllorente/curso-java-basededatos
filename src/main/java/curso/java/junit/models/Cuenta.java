package curso.java.junit.models;

import java.math.BigDecimal;

import curso.java.junit.excepciones.DineroInsuficienteException;

public class Cuenta {
    private String persona;
    private BigDecimal saldo;
    private Banco banco;

    public Cuenta(String persona, BigDecimal saldo) {
        this.saldo = saldo;
        this.persona = persona;
    }

    public String getPersona() {
        return persona;
    }

    public void setPersona(String persona) {
        this.persona = persona;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public void debito(BigDecimal cantidad) {
        BigDecimal nuevoSaldo = this.saldo.subtract(cantidad);
        if (nuevoSaldo.compareTo(BigDecimal.ZERO) < 0){
            throw new DineroInsuficienteException("Dinero Insuficiente");
        }
        this.saldo = nuevoSaldo;
    }

    public void credito(BigDecimal cantidad) {
        this.saldo = this.saldo.add(cantidad);
    }

//    @Override
//    public boolean equals(Object obj) {
//        if (!(obj instanceof Cuenta)) {
//            return false;
//        }
//        Cuenta c = (Cuenta) obj;
//        if (this.persona == null || this.saldo == null) {
//            return false;
//        }
//        return this.persona.equals(c.getPersona()) && this.saldo.equals(c.getSaldo());
//    }
}

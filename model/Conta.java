package model;

import lombok.Data;

@Data
public abstract class Conta implements IConta {
	
	private static final int AGENCIA_PADRAO = 1;
	private static int SEQUENCIAL = 1;

	protected int agencia;
	protected int numero;
	protected double saldo;
	protected Cliente cliente;

	public Conta(Cliente cliente) {
		this.agencia = Conta.AGENCIA_PADRAO;
		this.numero = SEQUENCIAL++;
		this.cliente = cliente;
	}

	@Override
	public void sacar(double valor) {
		if (valor <= 0) {
			System.out.println("Valor inválido para saque.");
		}else if (this.saldo < valor) {
			System.out.println("Saldo insuficiente.");
		} else {
			this.saldo -= valor;
			System.out.println("Saque realizado com sucesso.");
		}

	}

	@Override
	public void depositar(double valor) {
		if (valor <= 0) {
			System.out.println("Valor inválido para depósito.");
		}
		this.saldo += valor;
		System.out.println(String.format("Depósito de R$%.2f realizado com sucesso.", valor));
	}


	@Override
	public void transferir(double valor, IConta contaDestino) {
		if (valor <= 0) {
			System.out.println("Valor inválido para transferência.");
		} else if (contaDestino == null) {
			System.out.println("Conta de destino inválida.");
		} else if (this == contaDestino) {
			System.out.println("Não é permitido transferir para a mesma conta.");
		} else if (this.saldo < valor) {
			System.out.println("Saldo insuficiente para transferência.");
		} else {
			this.saldo -= valor;
			contaDestino.depositar(valor);
			System.out.println(String.format("Transferência de R$%.2f realizada com sucesso.", valor));
		}
	}

	protected void imprimirInfosComuns() {
		System.out.println(String.format("Titular: %s", this.cliente.getNome()));
		System.out.println(String.format("Agencia: %d", this.agencia));
		System.out.println(String.format("Numero: %d", this.numero));
		System.out.println(String.format("Saldo: %.2f", this.saldo));
	}
}

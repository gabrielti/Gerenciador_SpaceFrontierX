import spacex.LancamentoAbortadoException;

public abstract class Foguete {
        private String nome;
        private double carga;
        private double empuxo;
        private double combustivel;
        private String status;
        private double altura;
        private double peso;
        private double velocidade;
        private String fabricante;
        private String tipo;

        public Foguete(String nome, double carga, double empuxo, double combustivel, String status,
                    double altura, double peso, double velocidade, String fabricante, String tipo) {
            this.nome = nome;
            this.carga = carga;
            this.empuxo = empuxo;
            this.combustivel = combustivel;
            this.status = status;
            this.altura = altura;
            this.peso = peso;
            this.velocidade = velocidade;
            this.fabricante = fabricante;
            this.tipo = tipo;
        }
        public abstract void lançar() throws LancamentoAbortadoException;

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public double getCarga() {
            return carga;
        }

        public void setCarga(double carga) {
            this.carga = carga;
        }

        public double getEmpuxo() {
            return empuxo;
        }

        public void setEmpuxo(double empuxo) {
            this.empuxo = empuxo;
        }

        public double getCombustivel() {
            return combustivel;
        }

        public void setCombustivel(double combustivel) {
            this.combustivel = combustivel;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public double getAltura() {
            return altura;
        }

        public void setAltura(double altura) {
            this.altura = altura;
        }

        public double getPeso() {
            return peso;
        }

        public void setPeso(double peso) {
            this.peso = peso;
        }

        public double getVelocidade() {
            return velocidade;
        }

        public void setVelocidade(double velocidade) {
            this.velocidade = velocidade;
        }

        public String getFabricante() {
            return fabricante;
        }

        public void setFabricante(String fabricante) {
            this.fabricante = fabricante;
        }

        public String getTipo() {
            return tipo;
        }

        public void setTipo(String tipo) {
            this.tipo = tipo;
        }

        public abstract void lançar();
    }


public class Falcon9 extends Foguete {

    public Falcon9(String nome, double carga, double empuxo, double combustivel, String status,
                   double altura, double peso, double velocidade, String fabricante, String tipo) {
        super(nome, carga, empuxo, combustivel, status, altura, peso, velocidade, fabricante, tipo);
    }

    @Override
    public void lançar() throws LancamentoAbortadoException {
        if (getCombustivel() < 100) {
            throw new LancamentoAbortadoException("Falcon9: Combustível insuficiente para decolagem segura!");
        }
        System.out.println("Iniciando o lançamento do foguete " + getNome() + "...");
        System.out.println("Status: " + getStatus());
        System.out.println("Empuxo: " + getEmpuxo() + " N");
        System.out.println("Velocidade: " + getVelocidade() + " km/h");

        setCombustivel(getCombustivel() * 0.9);
        setStatus("Em voo");
        System.out.println("Lançamento realizado com sucesso! Foguete " + getNome() + " está em voo.");
        System.out.println("Combustível restante após decolagem: " + getCombustivel() + " L.");
    }
}


public class Starship extends Foguete {

    public Starship(String nome, double carga, double empuxo, double combustivel, String status,
                    double altura, double peso, double velocidade, String fabricante, String tipo) {
        super(nome, carga, empuxo, combustivel, status, altura, peso, velocidade, fabricante, tipo);
    }

    @Override
    public void lançar() throws LancamentoAbortadoException {
        if (getCombustivel() < 100) {
            throw new LancamentoAbortadoException("Falcon9: Combustível insuficiente para decolagem segura!");
        }
        System.out.println("Iniciando o lançamento do foguete " + getNome() + "...");
        System.out.println("Status: " + getStatus());
        System.out.println("Empuxo: " + getEmpuxo() + " N");
        System.out.println("Velocidade: " + getVelocidade() + " km/h");

        setCombustivel(getCombustivel() * 0.9);
        setStatus("Em voo");
        System.out.println("Lançamento realizado com sucesso! Foguete " + getNome() + " está em voo.");
        System.out.println("Combustível restante após decolagem: " + getCombustivel() + " L.");
    }
}

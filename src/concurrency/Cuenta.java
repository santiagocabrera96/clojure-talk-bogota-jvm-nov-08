package concurrency;

import java.util.ArrayList;
import java.util.List;

public class Cuenta {
    int saldo;
    List<Integer> historial = new ArrayList<>();
    public synchronized void hacerMovimiento(int monto) {
        saldo += monto;
        historial.add(monto);
    }
    @Override
    public String toString() {
        return String.format("{:saldo %d, :historial %s}", saldo, historial);
    }
}

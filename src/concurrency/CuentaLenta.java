package concurrency;

import java.util.ArrayList;
import java.util.List;

public class CuentaLenta {
    int saldo;
    List<Integer> historial = new ArrayList<>();

    public synchronized void hacerMovimiento(int monto) throws InterruptedException {
        saldo += monto;
        Thread.sleep(3000);
        historial.add(monto);
    }

    @Override
    public String toString() {
        return String.format("{:saldo %d, :historial %s}", saldo, historial);
    }
}

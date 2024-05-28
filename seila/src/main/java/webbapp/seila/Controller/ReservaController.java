package webbapp.seila.Controller;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import webbapp.seila.Model.Reserva;
import webbapp.seila.Service.ReservaService;

@Controller
@RequestMapping("/reservas")
public class ReservaController {

    private final ReservaService reservaService;

    @Autowired
    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @PostMapping
    public String processReserva(@RequestParam("nomeCompleto") String nomeCompleto,
                                 @RequestParam("modeloCarro") String modeloCarro,
                                 @RequestParam("telefone") String telefone,
                                 @RequestParam("cpf") String cpf,
                                 @RequestParam("email") String email,
                                 @RequestParam("localRetirada") String localRetirada,
                                 @RequestParam("dataRetirada") String dataRetiradaStr,
                                 @RequestParam("dataDevolucao") String dataDevolucaoStr) {
        // Transforma as datas de String em Date
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dataRetirada = null;
        Date dataDevolucao = null;
        try {
            dataRetirada = dateFormat.parse(dataRetiradaStr);
            dataDevolucao = dateFormat.parse(dataDevolucaoStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Cria um objeto Reserva com os dados do formulário
        Reserva reserva = new Reserva();
        reserva.setNomeCompleto(nomeCompleto);
        reserva.setModeloCarro(modeloCarro);
        reserva.setTelefone(telefone);
        reserva.setCpf(cpf);
        reserva.setEmail(email);
        reserva.setLocalRetirada(localRetirada);
        reserva.setDataRetirada(dataRetirada);
        reserva.setDataDevolucao(dataDevolucao);

        // Salva a reserva no banco de dados
        reservaService.salvarReserva(reserva);

        // Redireciona para uma página de sucesso
        return "redirect:/sucesso";
    }
}

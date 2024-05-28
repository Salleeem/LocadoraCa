package webbapp.seila.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "index"; // Retorna o nome do arquivo HTML (sem a extensão .html)
    }

    @GetMapping("/reservas")
    public String reservas() {
        return "reservas"; // Retorna o nome do arquivo HTML (sem a extensão .html)
    }

    @GetMapping("/sucesso")
    public String sucesso() {
        return "sucesso";
    }
}

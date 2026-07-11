package com.turismotec.ProyectoGraducacionTurismoTec.Controller;

import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.Mesas;
import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.ReservaMesa;
import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.ReservasHabitaciones;
import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.Usuario;
import com.turismotec.ProyectoGraducacionTurismoTec.model.service.IMesasService;
import com.turismotec.ProyectoGraducacionTurismoTec.model.service.IReservaMesaService;
import com.turismotec.ProyectoGraducacionTurismoTec.model.service.IUsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = {"${frontend.url}"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/public/v1")
public class ReservaMesaController {
    @Autowired
    private IReservaMesaService reservaMesaService;

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IMesasService mesasService;

    @GetMapping("/reservaMesa")
    public List<ReservaMesa> findAll(){
        return reservaMesaService.findAll();
    }

    @GetMapping("/reservasDeUsuarioMesas/{idUsuario}")
    public List<ReservaMesa> reservaByIdUser(@PathVariable Long idUsuario){
        List<ReservaMesa> allReservas = reservaMesaService.findAll();
        List<ReservaMesa> reservasFiltradas = new ArrayList<>();

        for(ReservaMesa reservasMesas: allReservas){
            if(reservasMesas.getUsuario().getId_Usuario().equals(idUsuario)){
                reservasFiltradas.add(reservasMesas);
            }
        }
        return  reservasFiltradas;
    }

    @GetMapping("/reservasDeMesasDeAsociados/{idUsuario}")
    public List<ReservaMesa> reservasDeMesasDeAsociados(@PathVariable Long idUsuario){
        List<ReservaMesa> allReservas = reservaMesaService.findAll();
        List<ReservaMesa> reservasFiltradas = new ArrayList<>();

        for(ReservaMesa reservasMesas: allReservas){
            if(reservasMesas.getMesas().getRestaurante().getUsuario().getId_Usuario().equals(idUsuario)){
                reservasFiltradas.add(reservasMesas);
            }
        }
        return  reservasFiltradas;
    }

    @GetMapping("/horaReservada/{fechaReserva}/{idMesa}")
    public List<String> horaReservada(@PathVariable String fechaReserva, @PathVariable Long idMesa) throws ParseException {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        Date fechaEvaluar = formatter.parse(fechaReserva);
        List<ReservaMesa> allReservas = reservaMesaService.findAll();
        List<String> horasDeMesa = new ArrayList<>();
        for (ReservaMesa reservaMesa:allReservas){
            Date fechaReservaDB = reservaMesa.getFechaReserva();
            if (formatter.format(fechaReservaDB).equals(formatter.format(fechaEvaluar)) && reservaMesa.getMesas().getIdMesa().equals(idMesa)) {
                horasDeMesa.add(reservaMesa.getHoraReserva());
            }
        }

        return horasDeMesa;
    }

    @GetMapping("/reservaMesa/{id}")
    public ReservaMesa findById(@PathVariable Long id){
        return reservaMesaService.findById(id);
    }

    @PostMapping("/reservaMesa/{idUsuario}/{idMesa}")
    public ReservaMesa save(@RequestBody ReservaMesa reservaMesa, @PathVariable Long idUsuario, @PathVariable Long idMesa){

        Usuario usuario = usuarioService.findById(idUsuario);
        Mesas mesas = mesasService.findById(idMesa);

        reservaMesa.setUsuario(usuario);
        reservaMesa.setMesas(mesas);

        return  reservaMesaService.save(reservaMesa);
    }

    @PutMapping("/reservaMesa/{id}")
    public ReservaMesa update(@RequestBody ReservaMesa reservaMesa, @PathVariable Long id){
        ReservaMesa auxUser =  reservaMesaService.findById(id);
        auxUser.setEstadoReserva(reservaMesa.isEstadoReserva());
        auxUser.setHoraReserva(reservaMesa.getHoraReserva());
        auxUser.setFechaReserva(reservaMesa.getFechaReserva());
        return reservaMesaService.save(auxUser);
    }

    //Confirmar reserva
    @PutMapping("/actualizarEstado/{reserva}/{id}")
    public ReservaMesa acrualizarEstado(@PathVariable Boolean reserva, @PathVariable Long id){
        ReservaMesa auxUser =  reservaMesaService.findById(id);
        auxUser.setEstadoReserva(reserva);
        return reservaMesaService.save(auxUser);
    }

    @DeleteMapping("/reservaMesa/{id}")
    public void delete(@PathVariable Long id){
        reservaMesaService.delete(id);
    }

    //Reportes
    @GetMapping("/reservasDeMesasPorYearYMes/{year}/{idPersona}")
    public List<String> reservasDeMesasPorYearYMes(@PathVariable int year,@PathVariable Long idPersona){
        return reservaMesaService.reservasDeMesasPorYearYMes(year,idPersona);
    }

    @GetMapping("/reservasDeMesasPorYearYMesParaAdmin/{year}")
    public List<String> reservasDeMesasPorYearYMesParaAdmin(@PathVariable int year){
        return reservaMesaService.reservasDeMesasPorYearYMesParaAdmin(year);
    }
}

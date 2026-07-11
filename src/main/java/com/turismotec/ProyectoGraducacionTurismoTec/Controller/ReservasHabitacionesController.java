package com.turismotec.ProyectoGraducacionTurismoTec.Controller;

import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.*;
import com.turismotec.ProyectoGraducacionTurismoTec.model.service.*;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.ListTokenSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = {"${frontend.url}"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/public/v1")
public class ReservasHabitacionesController {

    @Autowired
    private IReservasHabitacionesService reservasHabitacionesService;

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IHabitacionesService habitacionesService;

    @GetMapping("/reservasHabitaciones")
    public List<ReservasHabitaciones> findAll(){
        return reservasHabitacionesService.findAll();
    }

    @GetMapping("/reservasDeUsuario/{idUsuario}")
    public List<ReservasHabitaciones> reservaByIdUser(@PathVariable Long idUsuario){
        List<ReservasHabitaciones> allReservas = reservasHabitacionesService.findAll();
        List<ReservasHabitaciones> reservasFiltradas = new ArrayList<>();

        for(ReservasHabitaciones reservasHabitaciones: allReservas){
            if(reservasHabitaciones.getUsuario().getId_Usuario().equals(idUsuario)){
                reservasFiltradas.add(reservasHabitaciones);
            }
        }
        return  reservasFiltradas;
    }

    @GetMapping("/reservasDeHotelesDeAsociados/{idUsuario}")
    public List<ReservasHabitaciones> ReservasDeHotelesDeAsociados(@PathVariable Long idUsuario){
        List<ReservasHabitaciones> allReservas = reservasHabitacionesService.findAll();
        List<ReservasHabitaciones> reservasFiltradas = new ArrayList<>();

        for(ReservasHabitaciones reservasHabitaciones: allReservas){
            if(reservasHabitaciones.getHabitaciones().getHoteles().getUsuario().getId_Usuario().equals(idUsuario)){
                reservasFiltradas.add(reservasHabitaciones);
            }
        }
        return  reservasFiltradas;
    }

    @GetMapping("/reservasHabitaciones/{id}")
    public ReservasHabitaciones findById(@PathVariable Long id){
        return reservasHabitacionesService.findById(id);
    }

    @GetMapping("/fechasReservadas/{idHabitacion}")
    public List<Date> fechasReservadas(@PathVariable Long idHabitacion){
        return reservasHabitacionesService.fechasNoDisponibles(idHabitacion);
    }

    @PostMapping("/reservasHabitaciones/{idUsuario}/{idHabitacion}")
    public ReservasHabitaciones save(@RequestBody ReservasHabitaciones reservasHabitaciones,@PathVariable Long idUsuario, @PathVariable Long idHabitacion){
        long currentTimeMillis = System.currentTimeMillis();
        Date date = new Date(currentTimeMillis);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String hora = sdf.format(date);

        Usuario usuario = usuarioService.findById(idUsuario);
        Habitaciones habitaciones = habitacionesService.findById(idHabitacion);

        reservasHabitaciones.setHabitaciones(habitaciones);
        reservasHabitaciones.setUsuario(usuario);
        reservasHabitaciones.setHoraReserva(hora);
        reservasHabitaciones.setEstadoReserva(false);
        return  reservasHabitacionesService.save(reservasHabitaciones);
    }

    @PutMapping("/reservasHabitaciones/{id}")
    public ReservasHabitaciones update(@RequestBody ReservasHabitaciones reservasHabitaciones, @PathVariable Long id){
        ReservasHabitaciones auxUser =  reservasHabitacionesService.findById(id);
        auxUser.setFechaIncioReserva(reservasHabitaciones.getFechaIncioReserva());
        auxUser.setFechaFinReserva(reservasHabitaciones.getFechaFinReserva());
        auxUser.setEstadoReserva(reservasHabitaciones.isEstadoReserva());
        auxUser.setHoraReserva(reservasHabitaciones.getHoraReserva());
        return reservasHabitacionesService.save(auxUser);
    }

    //Confirmar reserva
    @PutMapping("/actualizarEstadoHabitacion/{reserva}/{id}")
    public ReservasHabitaciones acrualizarEstado(@PathVariable Boolean reserva, @PathVariable Long id){
        ReservasHabitaciones auxUser =  reservasHabitacionesService.findById(id);
        auxUser.setEstadoReserva(reserva);
        return reservasHabitacionesService.save(auxUser);
    }

    @DeleteMapping("/reservasHabitaciones/{id}")
    public void delete(@PathVariable Long id){
        reservasHabitacionesService.delete(id);
    }


    //Reportes
    @GetMapping("/reservasPorYearYMesHabitaciones/{year}/{idPersona}")
    public List<String> reservasPorYearYMes(@PathVariable int year,@PathVariable Long idPersona){
        return reservasHabitacionesService.reservasPorYearYMes(year,idPersona);
    }

    @GetMapping("/reservasPorYearYMesHabitacionesParaAdmin/{year}")
    public List<String> reservasPorYearYMesParaAdmin(@PathVariable int year){
        return reservasHabitacionesService.reservasPorYearYMesParaAdmin(year);
    }
}

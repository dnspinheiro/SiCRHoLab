package modelo;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.CategoriaDeEvento;
import modelo.Sala;
import modelo.Turma;
import modelo.Usuario;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2013-06-17T15:28:16")
@StaticMetamodel(Reserva.class)
public class Reserva_ { 

    public static volatile SingularAttribute<Reserva, Turma> turma;
    public static volatile SingularAttribute<Reserva, Date> dataHoraInicioEvento;
    public static volatile SingularAttribute<Reserva, String> status;
    public static volatile SingularAttribute<Reserva, Usuario> usuario;
    public static volatile SingularAttribute<Reserva, CategoriaDeEvento> categoriaEvento;
    public static volatile SingularAttribute<Reserva, Long> codigoReserva;
    public static volatile SingularAttribute<Reserva, Date> dataHora;
    public static volatile SingularAttribute<Reserva, Sala> sala;
    public static volatile SingularAttribute<Reserva, String> descricao;
    public static volatile SingularAttribute<Reserva, Date> dataHoraFimEvento;
    public static volatile SingularAttribute<Reserva, String> periodicidade;

}
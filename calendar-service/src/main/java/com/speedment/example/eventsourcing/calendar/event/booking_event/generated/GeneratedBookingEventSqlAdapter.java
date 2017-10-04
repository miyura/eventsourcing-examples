package com.speedment.example.eventsourcing.calendar.event.booking_event.generated;

import com.speedment.common.annotation.GeneratedCode;
import com.speedment.common.injector.annotation.ExecuteBefore;
import com.speedment.common.injector.annotation.WithState;
import com.speedment.example.eventsourcing.calendar.event.booking_event.BookingEvent;
import com.speedment.example.eventsourcing.calendar.event.booking_event.BookingEventImpl;
import com.speedment.example.eventsourcing.calendar.event.booking_event.generated.GeneratedBookingEvent.Type;
import com.speedment.runtime.config.Project;
import com.speedment.runtime.config.identifier.TableIdentifier;
import com.speedment.runtime.core.component.ProjectComponent;
import com.speedment.runtime.core.component.sql.SqlPersistenceComponent;
import com.speedment.runtime.core.component.sql.SqlStreamSupplierComponent;
import com.speedment.runtime.core.component.sql.SqlTypeMapperHelper;
import com.speedment.runtime.core.exception.SpeedmentException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;
import static com.speedment.common.injector.State.RESOLVED;
import static com.speedment.runtime.core.internal.util.sql.ResultSetUtil.*;

/**
 * The generated Sql Adapter for a {@link
 * BookingEvent}
 * entity.
 * <p>
 * This file has been automatically generated by Speedment. Any changes made to
 * it will be overwritten.
 * 
 * @author Speedment
 */
@GeneratedCode("Speedment")
public abstract class GeneratedBookingEventSqlAdapter {
    
    private final TableIdentifier<BookingEvent> tableIdentifier;
    private SqlTypeMapperHelper<Object, UUID> bookingHelper;
    private SqlTypeMapperHelper<String, Type> typeHelper;
    private SqlTypeMapperHelper<Timestamp, LocalDateTime> bookFromHelper;
    private SqlTypeMapperHelper<Timestamp, LocalDateTime> bookToHelper;
    
    protected GeneratedBookingEventSqlAdapter() {
        this.tableIdentifier = TableIdentifier.of("booking_demo", "booking_demo", "booking");
    }
    
    @ExecuteBefore(RESOLVED)
    void installMethodName(@WithState(RESOLVED) SqlStreamSupplierComponent streamSupplierComponent,
            @WithState(RESOLVED) SqlPersistenceComponent persistenceComponent) {
        streamSupplierComponent.install(tableIdentifier, this::apply);
        persistenceComponent.install(tableIdentifier);
    }
    
    protected BookingEvent apply(ResultSet resultSet) throws SpeedmentException {
        final BookingEvent entity = createEntity();
        try {
            entity.setId(       resultSet.getLong(1)                            );
            entity.setBooking(  bookingHelper.apply(resultSet.getObject(2))     );
            entity.setType(     typeHelper.apply(resultSet.getString(3))        );
            entity.setVersion(  resultSet.getByte(4)                            );
            entity.setUserId(   getInt(resultSet, 5)                            );
            entity.setResource( resultSet.getString(6)                          );
            entity.setBookFrom( bookFromHelper.apply(resultSet.getTimestamp(7)) );
            entity.setBookTo(   bookToHelper.apply(resultSet.getTimestamp(8))   );
        } catch (final SQLException sqle) {
            throw new SpeedmentException(sqle);
        }
        return entity;
    }
    
    protected BookingEventImpl createEntity() {
        return new BookingEventImpl();
    }
    
    @ExecuteBefore(RESOLVED)
    void createHelpers(ProjectComponent projectComponent) {
        final Project project = projectComponent.getProject();
        bookingHelper = SqlTypeMapperHelper.create(project, BookingEvent.BOOKING, BookingEvent.class);
        typeHelper = SqlTypeMapperHelper.create(project, BookingEvent.TYPE, BookingEvent.class);
        bookFromHelper = SqlTypeMapperHelper.create(project, BookingEvent.BOOK_FROM, BookingEvent.class);
        bookToHelper = SqlTypeMapperHelper.create(project, BookingEvent.BOOK_TO, BookingEvent.class);
    }
}
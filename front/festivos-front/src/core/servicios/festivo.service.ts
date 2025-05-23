import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Festivo } from '../../shared/entidades/Festivo';
import { Observable } from 'rxjs';
import { FestivoDTO } from '../../shared/DTOs/FestivoDTO';

@Injectable({
  providedIn: 'root'
})
export class FestivoService {

private url: string;

constructor(private http: HttpClient) {
  this.url = `${environment.urlBase}festivos/`;
}
  
public esFestivo(idPais: number, año: number, mes: number, dia: number): Observable<boolean> {
  const ruta = `${this.url}verificar/${idPais}/${año}/${mes}/${dia}`;
  return this.http.get<boolean>(ruta);
}

public obtenerFestivos(idPais: number, año: number): Observable<FestivoDTO[]> {
  const ruta = `${this.url}listar/${idPais}/${año}`;
  return this.http.get<FestivoDTO[]>(ruta);
}

public listar(): Observable<Festivo[]> {
  return this.http.get<Festivo[]>(`${this.url}listar`);
}

public buscar(dato: string): Observable<Festivo[]> {
  return this.http.get<Festivo[]>(`${this.url}buscar/${dato}`);
}

public agregar(festivo: Festivo): Observable<Festivo> {
  return this.http.post<Festivo>(`${this.url}agregar`, festivo);
}

public modificar(festivo: Festivo): Observable<Festivo> {
  return this.http.put<Festivo>(`${this.url}modificar`, festivo);
}

public eliminar(id: number): Observable<boolean> {
  return this.http.delete<boolean>(`${this.url}eliminar/${id}`);
}

}

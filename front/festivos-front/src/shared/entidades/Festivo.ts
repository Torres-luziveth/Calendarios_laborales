import { Pais } from "./Pais";
import { Tipo } from "./Tipo";

export interface Festivo{
    
    id: number;
    pais: Pais;
    nombre: string;
    dia: number;
    mes: number;
    diaPascua: number;
    tipo: Tipo;

}
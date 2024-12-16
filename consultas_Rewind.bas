B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=StaticCode
Version=12.8
@EndOfDesignText@
'Code module
'Subs in this code module will be accessible from all modules.
Sub Process_Globals
	'These global variables will be declared once when the application starts.
	'These variables can be accessed from all modules.
	Public id_usuario As Int
End Sub

Public Sub cantidad_viajes
	Dim query As String = $"SELECT
	usuarios.id_usuarios, 
	usuarios.nombre, 
	usuarios.id_ciudad, 
	carga_inicio.total_viajes_historial
FROM
	usuarios
	INNER JOIN
	carga_inicio
	ON 
		usuarios.id_usuarios = carga_inicio.id_usuarios
viajes realizados
WHERE 
usuarios.id_usuarios = ${id_usuario}"$
End Sub

Public Sub km_recorridos
	Dim query As String = $"SELECT
	usuarios.id_usuarios, 
	usuarios.nombre,
	viajes_servicios.estado,
	ROUND(SUM((6371 * ACos(
Cos( radians(viajes_servicios.lat_partida ) ) * Cos( radians(viajes_servicios.lat_llegada) ) * Cos( radians(viajes_servicios.lon_llegada) - radians( viajes_servicios.lon_partida ) ) + Sin( radians(viajes_servicios.lat_partida ) ) * Sin( radians(viajes_servicios.lat_llegada) ) 
				        ) 
				    )),2)As distance
FROM
	usuarios
	INNER JOIN
	viajes_servicios
	ON 
		usuarios.id_usuarios = viajes_servicios.id_usuarios
		WHERE
		usuarios.id_usuarios = ${id_usuario} And viajes_servicios.estado = 5
		GROUP BY
		usuarios.id_usuarios"$
End Sub

Private Sub zonas_visitadas
	Dim query As String = $"SELECT
	usuarios.id_usuarios, 
	usuarios.nombre, 
	viajes_servicios.estado, 
	rt_ciudades.id_ciudad, 
	CASE ( WHERE rt_ciudades.nombre_ciudad = 'Acarigua 2' then
	'Acarigua'
	ELSE 
	rt_ciudades.nombre_ciudad
	END) as nombre_ciudad
FROM
	usuarios
	INNER JOIN
	viajes_servicios
	ON 
		usuarios.id_usuarios = viajes_servicios.id_usuarios
	INNER JOIN
	rt_ciudades
	ON 
		viajes_servicios.id_ciudad = rt_ciudades.id_ciudad
WHERE
	usuarios.id_usuarios = ${id_usuario} And
	viajes_servicios.estado = 5
GROUP BY
	usuarios.id_usuarios, 
	viajes_servicios.id_ciudad"$
End Sub
Public Sub dia_semana_favorito
	Dim query As String = $"SELECT
	usuarios.id_usuarios, 
	usuarios.nombre, 
	count(usuarios.id_usuarios) as Cantidad,
	(CASE 
	WHEN DAYOFWEEK(viajes_servicios.fecha) = 2 THEN
	'Lunes'
WHEN DAYOFWEEK(viajes_servicios.fecha) = 3 THEN
	'Martes'
		WHEN DAYOFWEEK(viajes_servicios.fecha) = 4 THEN
	'Miercoles'
		WHEN DAYOFWEEK(viajes_servicios.fecha) = 5 THEN
	'Jueves'
		WHEN DAYOFWEEK(viajes_servicios.fecha) = 6 THEN
	'viernes'
		WHEN DAYOFWEEK(viajes_servicios.fecha) = 7 THEN
	'Sabado'
		WHEN DAYOFWEEK(viajes_servicios.fecha) = 1 THEN
	'Domingo'
END) as dia_semana
FROM
	usuarios
	INNER JOIN
	viajes_servicios
	ON 
		usuarios.id_usuarios = viajes_servicios.id_usuarios
	INNER JOIN
	rt_ciudades
	ON 
		viajes_servicios.id_ciudad = rt_ciudades.id_ciudad
WHERE
	usuarios.id_usuarios = ${id_usuario} And
	viajes_servicios.estado = 5
	GROUP BY
	DAYOFWEEK(viajes_servicios.fecha)
	ORDER BY
	count(usuarios.id_usuarios) DESC
	LIMIT 1"$
End Sub

Public Sub horario_usuario
	Dim query As String = $"SELECT
	usuarios.id_usuarios, 
	usuarios.nombre,
	
	(CASE 
	WHEN IFNULL(diurno.Cantidad,0) > IFNULL(nocturno.Cantidad,0) THEN
	'Diurna'
	WHEN IFNULL(diurno.Cantidad,0) < IFNULL(nocturno.Cantidad,0) THEN
	'Nocturna'
			WHEN IFNULL(diurno.Cantidad,0) = IFNULL(nocturno.Cantidad,0) THEN
	'Ambas'
END) as persona,

	IFNULL(diurno.Cantidad,0) as 'cantidad_diurna',
	IFNULL(nocturno.Cantidad,0) as 'cantidad_nocturna'
	FROM
	usuarios 
	LEFT JOIN
(SELECT
	viajes_servicios.id_usuarios, 
	count(viajes_servicios.id_usuarios) AS Cantidad
FROM

	viajes_servicios
	
WHERE
viajes_servicios.id_usuarios = ${id_usuario} AND
	viajes_servicios.estado = 5 AND
	viajes_servicios.hora > '06:00:00' AND
		viajes_servicios.hora < '18:30:00'
GROUP BY
	viajes_servicios.id_usuarios) as diurno
ON
usuarios.id_usuarios = diurno.id_usuarios
LEFT JOIN

(SELECT
	viajes_servicios.id_usuarios, 
	count(viajes_servicios.id_usuarios) AS Cantidad
FROM
	viajes_servicios
WHERE
viajes_servicios.id_usuarios = ${id_usuario} AND
	viajes_servicios.estado = 5 AND
	viajes_servicios.hora < '06:00:00' AND
		viajes_servicios.hora > '18:30:00'
GROUP BY
	viajes_servicios.id_usuarios ) as nocturno
	ON
usuarios.id_usuarios = nocturno.id_usuarios
WHERE
usuarios.id_usuarios = ${id_usuario}
ORDER BY
usuarios.id_usuarios ASC"$
End Sub

Public Sub distancia_media_solicitud
	
	Dim query As String = $"SELECT
	 viajes_servicios.id_usuarios, 
	viajes_servicios.estado,
	ROUND(SUM((6371 * ACos(
Cos( radians(viajes_servicios.lat_partida ) ) * Cos( radians(viajes_servicios.lat_llegada) ) * Cos( radians(viajes_servicios.lon_llegada) - radians( viajes_servicios.lon_partida ) ) + Sin( radians(viajes_servicios.lat_partida ) ) * Sin( radians(viajes_servicios.lat_llegada) ) 
				        ) 
				    )),2) / 	 COUNT(viajes_servicios.id_usuarios) As distance_promedio,
						COUNT(viajes_servicios.id_usuarios) as cantidad
FROM

	viajes_servicios

		WHERE
		 viajes_servicios.id_usuarios= ${id_usuario} And viajes_servicios.estado = 5 
	
		GROUP BY
		 viajes_servicios.id_usuarios, 	Round((6371 * ACos(
Cos( radians(viajes_servicios.lat_partida ) ) * Cos( radians(viajes_servicios.lat_llegada) ) * Cos( radians(viajes_servicios.lon_llegada) - radians( viajes_servicios.lon_partida ) ) + Sin( radians(viajes_servicios.lat_partida ) ) * Sin( radians(viajes_servicios.lat_llegada) ) 
				        ) 
				    ),2) < 2, 	
						Round((6371 * ACos(
Cos( radians(viajes_servicios.lat_partida ) ) * Cos( radians(viajes_servicios.lat_llegada) ) * Cos( radians(viajes_servicios.lon_llegada) - radians( viajes_servicios.lon_partida ) ) + Sin( radians(viajes_servicios.lat_partida ) ) * Sin( radians(viajes_servicios.lat_llegada) ) 
				        ) 
				    ),2) >= 2 And 	Round((6371 * ACos(
Cos( radians(viajes_servicios.lat_partida ) ) * Cos( radians(viajes_servicios.lat_llegada) ) * Cos( radians(viajes_servicios.lon_llegada) - radians( viajes_servicios.lon_partida ) ) + Sin( radians(viajes_servicios.lat_partida ) ) * Sin( radians(viajes_servicios.lat_llegada) ) 
				        ) 
				    ),2) < 5,
						
							Round((6371 * ACos(
Cos( radians(viajes_servicios.lat_partida ) ) * Cos( radians(viajes_servicios.lat_llegada) ) * Cos( radians(viajes_servicios.lon_llegada) - radians( viajes_servicios.lon_partida ) ) + Sin( radians(viajes_servicios.lat_partida ) ) * Sin( radians(viajes_servicios.lat_llegada) ) 
				        ) 
				    ),2) >= 2
						ORDER BY
						COUNT(viajes_servicios.id_usuarios) DESC
						LIMIT 1"$
End Sub

Public Sub servicio_mas_solicitado
	Dim query As String = $"SELECT
	usuarios.id_usuarios, 
	usuarios.nombre, 
	viajes_servicios.id_servicios, 
	COUNT(viajes_servicios.id_servicios) as cantidad_servicios,
	(CASE 
	WHEN viajes_servicios.id_servicios IN (3,16,11) THEN
	'Economico'
		WHEN viajes_servicios.id_servicios IN (4,12,17) THEN
	'Confort'
			WHEN viajes_servicios.id_servicios IN (7,13,18) THEN
	'Mascotas'
			WHEN viajes_servicios.id_servicios IN (1,14,19) THEN
	'Motocicleta'
		WHEN viajes_servicios.id_servicios IN (8) THEN
	'Solo mujeres'
END) as nombre_servicio,
	servicios.nombre
FROM
	usuarios
	INNER JOIN
	viajes_servicios
	ON 
		usuarios.id_usuarios = viajes_servicios.id_usuarios
	INNER JOIN
	servicios
	ON 
		viajes_servicios.id_servicios = servicios.id_servicios
		WHERE
		viajes_servicios.id_usuarios= ${id_usuario}
		GROUP BY
		viajes_servicios.id_servicios  IN (3,16,11), viajes_servicios.id_servicios IN (4,12,17), viajes_servicios.id_servicios IN (7,13,18), viajes_servicios.id_servicios IN (1,14,19), viajes_servicios.id_servicios IN (8)
		ORDER BY
	COUNT(viajes_servicios.id_servicios) DESC
	LIMIT 1"$
End Sub

Public Sub conductor_mas_atendido
	Dim query As String = $"SELECT
	usuarios.id_usuarios, 
	usuarios.nombre, 
COUNT(conductores.id_conductores) AS conteo_servicios,
	conductores.id_conductores, 
	conductores.nombre as nombre_conductor
FROM
	usuarios
	INNER JOIN
	viajes_servicios
	ON 
		usuarios.id_usuarios = viajes_servicios.id_usuarios
	INNER JOIN
	conductores
	ON 
		viajes_servicios.id_conductor = conductores.id_conductores
		WHERE
		viajes_servicios.estado = 5 AND
		usuarios.id_usuarios = ${id_usuario}
		GROUP BY
		conductores.id_conductores
		ORDER BY 
		COUNT(conductores.id_conductores) DESC
		LIMIT 1"$
End Sub

Public Sub dia_con_mas_viajes
	Dim query As String = $"SELECT
	usuarios.id_usuarios, 
	usuarios.nombre, 
	viajes_servicios.fecha,
	COUNT(usuarios.id_usuarios) as conteo_servicios_hechos_dia
FROM
	usuarios
	INNER JOIN
	viajes_servicios
	ON 
		usuarios.id_usuarios = viajes_servicios.id_usuarios
WHERE
viajes_servicios.estado = 5 AND usuarios.id_usuarios = ${id_usuario}
GROUP BY
	viajes_servicios.fecha
	ORDER BY
	COUNT(usuarios.id_usuarios) DESC, viajes_servicios.fecha ASC
	LIMIT 1"$
End Sub

Public Sub servicios_mas_largo_corto
	Dim corto As String = $"
SELECT
	usuarios.id_usuarios, 
	usuarios.nombre, 
	viajes_servicios.estado, 
	ROUND((6371 * ACos(
Cos( radians(viajes_servicios.lat_partida ) ) * Cos( radians(viajes_servicios.lat_llegada) ) * Cos( radians(viajes_servicios.lon_llegada) - radians( viajes_servicios.lon_partida ) ) + Sin( radians(viajes_servicios.lat_partida ) ) * Sin( radians(viajes_servicios.lat_llegada) ) 
				        ) 
				    ),2) AS distance, 
	viajes_servicios.lat_llegada, 
	viajes_servicios.lon_llegada, 
	viajes_servicios.nom_llegada, 
	viajes_servicios.nom_llegada_final
FROM
	usuarios
	INNER JOIN
	viajes_servicios
	ON 
		usuarios.id_usuarios = viajes_servicios.id_usuarios
WHERE
	usuarios.id_usuarios = ${id_usuario} AND
	viajes_servicios.estado = 5
ORDER BY
	ROUND((6371 * ACos(
Cos( radians(viajes_servicios.lat_partida ) ) * Cos( radians(viajes_servicios.lat_llegada) ) * Cos( radians(viajes_servicios.lon_llegada) - radians( viajes_servicios.lon_partida ) ) + Sin( radians(viajes_servicios.lat_partida ) ) * Sin( radians(viajes_servicios.lat_llegada) ) 
				        ) 
				    ),2) ASC
LIMIT 1
"$
	Dim largo As String = $"SELECT
	usuarios.id_usuarios, 
	usuarios.nombre, 
	viajes_servicios.estado, 
	ROUND((6371 * ACos(
Cos( radians(viajes_servicios.lat_partida ) ) * Cos( radians(viajes_servicios.lat_llegada) ) * Cos( radians(viajes_servicios.lon_llegada) - radians( viajes_servicios.lon_partida ) ) + Sin( radians(viajes_servicios.lat_partida ) ) * Sin( radians(viajes_servicios.lat_llegada) ) 
				        ) 
				    ),2) AS distance, 
	viajes_servicios.lat_llegada, 
	viajes_servicios.lon_llegada, 
	viajes_servicios.nom_llegada, 
	viajes_servicios.nom_llegada_final
FROM
	usuarios
	INNER JOIN
	viajes_servicios
	ON 
		usuarios.id_usuarios = viajes_servicios.id_usuarios
WHERE
	usuarios.id_usuarios = ${id_usuario} AND
	viajes_servicios.estado = 5
ORDER BY
	ROUND((6371 * ACos(
Cos( radians(viajes_servicios.lat_partida ) ) * Cos( radians(viajes_servicios.lat_llegada) ) * Cos( radians(viajes_servicios.lon_llegada) - radians( viajes_servicios.lon_partida ) ) + Sin( radians(viajes_servicios.lat_partida ) ) * Sin( radians(viajes_servicios.lat_llegada) ) 
				        ) 
				    ),2) DESC
LIMIT 1"$
End Sub